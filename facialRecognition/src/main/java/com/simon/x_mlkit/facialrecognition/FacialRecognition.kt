package com.simon.x_mlkit.facialrecognition

import android.graphics.Bitmap
import com.huawei.hms.mlsdk.MLAnalyzerFactory
import com.huawei.hms.mlsdk.common.MLApplication
import com.huawei.hms.mlsdk.common.MLFrame
import com.huawei.hms.mlsdk.face.MLFaceAnalyzer
import com.huawei.hms.mlsdk.face.MLFaceAnalyzerSetting
import kotlinx.coroutines.*
import java.io.IOException

class FacialRecognition {

    fun setUpAuth(accessToken:String, apiKey:String){
        MLApplication.getInstance().setAccessToken(accessToken);
        MLApplication.getInstance().setUserRegion(MLApplication.REGION_DR_UNKNOWN);
        MLApplication.getInstance().apiKey = apiKey
    }

    /**
     * Returns whether a face was detected,
     * @param bitmap the bitmap to scan for faces
     * /
     */
    suspend fun isFaceDetectedAsync(bitmap: Bitmap):Deferred<FacesDetectedResult?> = CoroutineScope(Dispatchers.IO).async{

        val isFaceDetected = CompletableDeferred<FacesDetectedResult?>()

        val faceAnalyzer = getFaceAnalyzer()
        val frame = MLFrame.fromBitmap(bitmap)
        val task = faceAnalyzer.asyncAnalyseFrame(frame)

        task.addOnSuccessListener {
            // Detection success.
            if(it.isNullOrEmpty()){
                isFaceDetected.complete(FacesDetectedResult(0))
                return@addOnSuccessListener
            }
            isFaceDetected.complete(FacesDetectedResult(it.size))
            return@addOnSuccessListener
        }.addOnFailureListener {
            // Detection failure.
            isFaceDetected.complete(FacesDetectedResult(0,it))
        }
        try {
                faceAnalyzer.stop()
        } catch (e: IOException) {
            // Exception handling.
            e.printStackTrace()
        }

        return@async isFaceDetected.await()
    }

    private fun getFaceAnalyzer(tracingAllowed:Boolean=false):MLFaceAnalyzer{
        // Method 1: Use customized parameter settings.
        // If the Full SDK mode is used for integration, set parameters based on the integrated model package.
        val setting = MLFaceAnalyzerSetting.Factory() // Set whether to detect key face points.
            .setKeyPointType(MLFaceAnalyzerSetting.TYPE_KEYPOINTS) // Set whether to detect facial features and expressions.
            .setFeatureType(MLFaceAnalyzerSetting.TYPE_FEATURES) // Enable only facial expression detection and gender detection.
            .setFeatureType(MLFaceAnalyzerSetting.TYPE_FEATURE_EMOTION or MLFaceAnalyzerSetting.TYPE_FEATURE_GENDAR) // Set whether to detect face contour points.
            .setShapeType(MLFaceAnalyzerSetting.TYPE_SHAPES) // Set whether to enable face tracking and specify the fast tracking mode.
            .setTracingAllowed(tracingAllowed, MLFaceAnalyzerSetting.MODE_TRACING_FAST) // Set the speed and precision of the detector.
            .setPerformanceType(MLFaceAnalyzerSetting.TYPE_PRECISION) // Set whether to enable pose detection (enabled by default).
            .setPoseDisabled(true)
            .create()
      return MLAnalyzerFactory.getInstance().getFaceAnalyzer(setting)
    }

}

data class FacesDetectedResult(val noOfFaces: Int, val exception:Throwable?=null)