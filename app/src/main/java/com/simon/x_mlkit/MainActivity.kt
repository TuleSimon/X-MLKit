package com.simon.x_mlkit

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import com.simon.x_mlkit.facialrecognition.FacesDetectedResult
import com.simon.x_mlkit.facialrecognition.FacialRecognition
import com.simon.x_mlkit.ui.theme.XMLKITTheme
import java.io.File

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      val succes = remember { mutableStateOf(false) }
      val imageUri = remember { mutableStateOf<Uri?>(null) }
      val result = remember { mutableStateOf<FacesDetectedResult?>(null) }

      XMLKITTheme {
        val cameraLauncher =
            rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicture()) {
                success ->
              succes.value = success
            }

        val context = LocalContext.current

        LaunchedEffect(key1 = succes.value) {
          if (succes.value) {
            imageUri.value?.let {
              val bitmap = MediaStore.Images.Media.getBitmap(this@MainActivity.contentResolver, it)
              bitmap?.let { result.value = FacialRecognition().isFaceDetectedAsync(bitmap).await() }
            }
          }
        }
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          Column(
              verticalArrangement = Arrangement.spacedBy(20.dp),
              horizontalAlignment = Alignment.CenterHorizontally,
              modifier = Modifier.fillMaxSize()) {
                if (imageUri.value != null && succes.value) {
                  AsyncImage(
                      model = imageUri.value,
                      modifier = Modifier.fillMaxWidth().fillMaxHeight(0.7f),
                      contentScale = ContentScale.Fit,
                      contentDescription = "Selected image",
                  )

                  Text(text = " ${result.value?.noOfFaces.toString()} faces detected")
                }

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(20.dp),
                    onClick = {
                      val uri = ComposeFileProvider.getImageUri(context)
                      imageUri.value = uri
                      cameraLauncher.launch(imageUri.value)
                    }) {
                      Text(text = "Click Here to Take Picture")
                    }
              }
        }
      }
    }
  }
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
