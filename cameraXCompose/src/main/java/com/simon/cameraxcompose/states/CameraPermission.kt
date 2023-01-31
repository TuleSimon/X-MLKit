package com.simon.cameraxcompose.states

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun requestCameraPermission():PermissionState{
    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )
    LaunchedEffect(){
        cameraPermissionState.launchPermissionRequest()
    }
    return cameraPermissionState
}