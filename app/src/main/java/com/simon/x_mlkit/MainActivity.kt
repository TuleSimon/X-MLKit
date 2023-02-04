package com.simon.x_mlkit

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.simon.cameraxcompose.CameraPreview
import com.simon.cameraxcompose.states.rememberCameraXState
import com.simon.cameraxcompose.states.switchOnFlash
import com.simon.cameraxcompose.takePhoto
import com.simon.x_mlkit.ui.theme.XMLKITTheme
import java.io.File

class MainActivity : ComponentActivity() {
  @OptIn(ExperimentalPermissionsApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      XMLKITTheme {
        val succes = remember { mutableStateOf(false) }
        val imageUri = remember { mutableStateOf<Uri?>(null) }

        LaunchedEffect(key1 = succes.value) {
          if (succes.value) {
            imageUri.value?.let {
              val bitmap = MediaStore.Images.Media.getBitmap(this@MainActivity.contentResolver, it)
              bitmap?.let {}
            }
          }
        }
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          Box(
              modifier = Modifier.fillMaxSize()) {
                val state = requestCameraPermission()

                LaunchedEffect(true) { state.launchPermissionRequest() }

                if (state.status.isGranted) {
                  val cameraState = rememberCameraXState()

                  CameraPreview(modifier = Modifier.fillMaxSize(), cameraState)

                  Button(
                      modifier = Modifier.align(Alignment.BottomCenter)
                          .fillMaxWidth(0.4f)
                          .border(2.dp,colorScheme.primary, CircleShape).padding(10.dp)
                          .background(colorScheme.primary, CircleShape).clip(CircleShape)
                          .aspectRatio(1f),
                      contentPadding = PaddingValues(20.dp),
                      onClick = { cameraState.value.takePhoto() }) {
                        Text(text = "CAPTURE PHOTO")
                        //
                      }

                    IconButton(
                        modifier = Modifier.align(Alignment.BottomStart)
                            .width(15.dp)
                            .padding(10.dp)
                            .background(colorScheme.primary, CircleShape).clip(CircleShape)
                            .aspectRatio(1f),
                        onClick = { cameraState.switchOnFlash() }) {

                        Icon(painter = painterResource(R.drawable.baseline_flashlight_on_24), modifier = Modifier.size(15.dp),
                        contentDescription = null)

                    }

                }
              }
        }
      }
    }
  }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun requestCameraPermission(): PermissionState {
  val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

  return cameraPermissionState
}

class ComposeFileProvider : FileProvider(R.xml.filepaths) {
  companion object {
    fun getImageUri(context: Context): Uri {
      val directory = File(context.cacheDir, "images")
      directory.mkdirs()
      val file =
          File.createTempFile(
              "selected_image_",
              ".jpg",
              directory,
          )
      val authority = context.packageName + ".fileprovider"
      return getUriForFile(
          context,
          authority,
          file,
      )
    }
  }
}
