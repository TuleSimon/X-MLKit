package com.simon.cameraxcompose.states

import androidx.camera.core.ImageCapture
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun rememberCameraXState(): MutableState<CameraXState> {

    val imageCapture = ImageCapture.Builder().build()

    val state =  remember{
        mutableStateOf(CameraXState(imageCapture))
    }
    return state
}

fun MutableState<CameraXState>.updateImageCapture(imageCapture: ImageCapture  ){
    value = value.copy(imageCapture = imageCapture)
}

 data class CameraXState(val imageCapture: ImageCapture)