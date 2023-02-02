package com.simon.cameraxcompose

import android.Manifest
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY
import androidx.camera.core.ImageCapture.FLASH_MODE_ON
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.simon.cameraxcompose.states.CameraXState
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Composable
fun CameraPreview(modifier: Modifier,
                   state: MutableState<CameraXState>) {

    val cameraExecutor: ExecutorService  = Executors.newSingleThreadExecutor()


    AndroidView(
        modifier = modifier,
        factory = { context ->
            PreviewView(context).apply {
                layoutParams =
                    ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            }
        },
        update = { previewView ->
            startCamera(previewView,state)
        }
    )
}


internal fun startCamera(previewView: PreviewView,state: MutableState<CameraXState>) {
    val cameraProviderFuture = ProcessCameraProvider.getInstance(previewView.context)

    cameraProviderFuture.addListener({
        // Used to bind the lifecycle of cameras to the lifecycle owner
        val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

        // Preview
        val preview = Preview.Builder()
            .build()
            .also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

        // Select back camera as a default
        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
        try {
            // Unbind use cases before rebinding
            cameraProvider.unbindAll()

            // Bind use cases to camera
            cameraProvider.bindToLifecycle(
                state.value.lifecycleOwner, cameraSelector, preview,state.value.imageCapture)


        } catch(exc: Exception) {
            Log.e("XCAMERA", "Use case binding failed", exc)
        }

    }, ContextCompat.getMainExecutor(previewView.context))
}


private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
private fun ImageCapture.takePhoto(, context: Context) {

    val imageCapture = this
    // Get a stable reference of the modifiable image capture use case
//    val imageCapture =ImageCapture.Builder().setFlashMode(FLASH_MODE_ON)
//        .setJpegQuality(CAPTURE_MODE_MAXIMIZE_QUALITY).build() ?: return

    // Create time stamped name and MediaStore entry.
    val name = SimpleDateFormat(FILENAME_FORMAT, Locale.getDefault())
        .format(System.currentTimeMillis())
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, name)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
        }
    }

    // Create output options object which contains file + metadata
    val outputOptions = ImageCapture.OutputFileOptions
        .Builder(context.contentResolver,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues)
        .build()

    // Set up image capture listener, which is triggered after photo has
    // been taken
    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onError(exc: ImageCaptureException) {
                Log.e("CAPTURE", "Photo capture failed: ${exc.message}", exc)
            }

            override fun
                    onImageSaved(output: ImageCapture.OutputFileResults){
                val msg = "Photo capture succeeded: ${output.savedUri}"
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                Log.d("SAVED", msg)
            }
        }
    )
}


private val REQUIRED_PERMISSIONS =
    mutableListOf (
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO
    ).apply {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }.toTypedArray()

