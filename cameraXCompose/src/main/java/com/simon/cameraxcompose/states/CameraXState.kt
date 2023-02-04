package com.simon.cameraxcompose.states

import android.content.Context
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner

@Composable
fun rememberCameraXState(): MutableState<CameraXState> {

    val imageCapture = remember{
        mutableStateOf( ImageCapture.Builder().build())}

    val lifecycleOwner = LocalLifecycleOwner.current

    val context = LocalContext.current
    val state =  remember{
        mutableStateOf(CameraXState(imageCapture,lifecycleOwner,context))
    }
    return state
}

fun MutableState<CameraXState>.updateImageCapture(imageCapture: ImageCapture  ){
    value = value.copy(imageCapture = imageCapture)
}

fun MutableState<CameraXState>.switchToFrontCamera(imageCapture: ImageCapture  ){
    value = value.copy(imageCapture = imageCapture)
}

fun MutableState<CameraXState>.switchToBackCamera(imageCapture: ImageCapture  ){
    value = value.copy(imageCapture = imageCapture)
}
/**
 * see the flash mode to on, when user takes a picture, flash light does come on
 */
fun MutableState<CameraXState>.switchOnFlash( ){
    value.imageCapture.value =value.imageCapture.value.also {  it.flashMode = FLASH_MODE_ON}
}

/**
 * see the flash mode to off, when user takes a picture, flash light doesn't come on
 */
fun MutableState<CameraXState>.switchOffFlash( ){
    value.imageCapture.value =value.imageCapture.value.also {  it.flashMode = FLASH_MODE_OFF}
}

/**
 * see the flash mode to auto, when user takes a picture, if environment is dark, flash comes on,
 * else flash stays off
 */
fun MutableState<CameraXState>.flashAuto( ){
    value.imageCapture.value =value.imageCapture.value.also {  it.flashMode = FLASH_MODE_AUTO}
}

 data class CameraXState(val imageCapture: MutableState<ImageCapture>, val lifecycleOwner: LifecycleOwner,
 val context: Context
 ){
     fun getFlashMode(): CameraXFlashMode{
         return when(imageCapture.value.flashMode){
             FLASH_MODE_ON ->  CameraXFlashMode.ON
             FLASH_MODE_OFF ->  CameraXFlashMode.OFF
             else  ->  CameraXFlashMode.AUTO
         }
     }
 }

enum class CameraXFlashMode( ){
    ON,OFF,AUTO
}