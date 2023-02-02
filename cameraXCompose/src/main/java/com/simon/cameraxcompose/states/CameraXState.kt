package com.simon.cameraxcompose.states

import android.content.Context
import androidx.camera.core.ImageCapture
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner

@Composable
fun rememberCameraXState(): MutableState<CameraXState> {

    val imageCapture = ImageCapture.Builder().build()

    val lifecycleOwner = LocalLifecycleOwner.current

    val state =  remember{
        mutableStateOf(CameraXState(imageCapture,lifecycleOwner))
    }
    return state
}

fun MutableState<CameraXState>.updateImageCapture(imageCapture: ImageCapture  ){
    value = value.copy(imageCapture = imageCapture)
}

 data class CameraXState(val imageCapture: ImageCapture, val lifecycleOwner: LifecycleOwner,
 val context: Context
 )