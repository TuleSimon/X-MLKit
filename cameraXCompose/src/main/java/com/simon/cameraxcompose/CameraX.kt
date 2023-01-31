package com.simon.cameraxcompose

import android.Manifest
import android.os.Build
import android.view.ViewGroup
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.simon.cameraxcompose.states.requestCameraPermission

@Composable
internal fun CameraPreview(modifier: Modifier) {
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

      }
  )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun requestPermission():PermissionState{
    return requestCameraPermission()
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

