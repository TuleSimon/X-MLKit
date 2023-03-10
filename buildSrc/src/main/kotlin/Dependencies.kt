package com.simon.x_mlkit

import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

  const val ANDROID_CORE_KTS = "androidx.core:core-ktx:1.9.0"
  const val ANDROID_LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"
  const val ANDROIDX_ACTIVITY_COMPOSE = "androidx.activity:activity-compose:1.6.1"
  const val ANDROIDX_COMPOSE_UI = "androidx.compose.ui:ui:${Versions.COMPOSE_VERSION}"
  const val ANDROIDX_COMPOSE_UI_TOOLING_PREVIEW =
      "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE_VERSION}"
  const val ANDROIDX_COMPOSE_MATERIAL3 = "androidx.compose.material3:material3:1.1.0-alpha04"

  const val JUNIT = "junit:junit:4.13.2"
  const val ANDROIDX_JUNIT_TEST = "androidx.test.ext:junit:1.1.5"
  const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.5.1"
  const val ANDROIDX_COMPOSE_UI_TEST_JUNIT4 =
      "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE_VERSION}"
  const val ANDROIDX_COMPOSE_UI_TOOLING =
      "androidx.compose.ui:ui-tooling:${Versions.COMPOSE_VERSION}"
  const val ANDROIDX_COMPOSE_UI_MANIFEST_TEST =
      "androidx.compose.ui:ui-test-manifest:${Versions.COMPOSE_VERSION}"

  // Import the base SDK.
  const val HUAWEI_COMPUTER_VISION_OCR =
      "com.huawei.hms:ml-computer-vision-ocr:${Versions.HUAWEI_VERSION}"
  // Import the Latin-based language model package.
  const val HUAWEI_COMPUTER_VISION_OCR_LATIN =
      "com.huawei.hms:ml-computer-vision-ocr-latin-model:${Versions.HUAWEI_VERSION}"
  // Import the Japanese and Korean model package.
  const val HUAWEI_COMPUTER_VISION_OCR_JK =
      "com.huawei.hms:ml-computer-vision-ocr-jk-model:${Versions.HUAWEI_VERSION}"
  // Import the Chinese and English model package.
  const val HUAWEI_COMPUTER_VISION_OCR_CN =
      "com.huawei.hms:ml-computer-vision-ocr-cn-model:${Versions.HUAWEI_VERSION}"

  // Import the base Sdk
  const val HUAWEI_COMPUTER_VISION_FACE_ =
      "com.huawei.hms:ml-computer-vision-face:${Versions.HUAWEI_FACE_SDK_VERSION}"

  const val HUAWEI_COMPUTER_VISION_FACE_SHAPE_POINT_MODEL =
      "com.huawei.hms:ml-computer-vision-face-shape-point-model:${Versions.HUAWEI_FACE_SDK_VERSION}"
  // Import the facial expression detection model package.
  const val HUAWEI_COMPUTER_VISION_FACE_EMOTION_MODEL =
      "com.huawei.hms:ml-computer-vision-face-emotion-model:${Versions.HUAWEI_FACE_SDK_VERSION}"
  // Import the facial feature detection model package.
  const val HUAWEI_COMPUTER_VISION_FACE_FEATURE_MODEL =
      "com.huawei.hms:ml-computer-vision-face-feature-model:${Versions.HUAWEI_FACE_SDK_VERSION}"
  // Import the 3D face detection model package.
  const val HUAWEI_COMPUTER_VISION_FACE_VISION_FACE_3D_MODEL =
      "com.huawei.hms:ml-computer-vision-face-3d-model:${Versions.HUAWEI_FACE_SDK_VERSION}"

  // COROUTINES
  const val KOTLINX_COROUTINES =
      "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINE_VERSION}"
  const val KOTLINX_COROUTINES_ANDROID =
      "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINE_VERSION}"
  const val KOTLINX_COROUTINES_PLAY_SERVICES =
      "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.1.1"

  // COIL
  const val COIL_COMPOSE = "io.coil-kt:coil-compose:2.2.2"

    //MLKIT
    const val  MLKIT_FACE_DETECTION = "com.google.mlkit:face-detection:16.1.5"

  //CAMERAX

  const val CAMERA_CORE = "androidx.camera:camera-core:${Versions.CAMERAX_VERSION}"
  const val CAMERA_CAMERA2 = "androidx.camera:camera-camera2:${Versions.CAMERAX_VERSION}"
  const val CAMERA_LIFECYCLE = "androidx.camera:camera-lifecycle:${Versions.CAMERAX_VERSION}"
  const val CAMERA_VIDEO = "androidx.camera:camera-video:${Versions.CAMERAX_VERSION}"
  const val CAMERA_VIEW = "androidx.camera:camera-view:${Versions.CAMERAX_VERSION}"
  const val CAMERA_EXTENSIONS = "androidx.camera:camera-extensions:${Versions.CAMERAX_VERSION}"


  //ACCOMPANIST
  const val ACCOMPANIST_PERMISSIONS =  "com.google.accompanist:accompanist-permissions:${Versions.ACCOMPANIST_PERMISSION_VERSION}"

}

// android test dependencies
fun DependencyHandler.androidTest() {
  testImplementation(Dependencies.JUNIT)
  androidTestImplementation(Dependencies.ANDROIDX_JUNIT_TEST)
  androidTestImplementation(Dependencies.ESPRESSO_CORE)
  androidTestImplementation(Dependencies.ANDROIDX_COMPOSE_UI_TEST_JUNIT4)
  debugImplementation(Dependencies.ANDROIDX_COMPOSE_UI_TOOLING)
  debugImplementation(Dependencies.ANDROIDX_COMPOSE_UI_MANIFEST_TEST)
}

// huawei
fun DependencyHandler.Huawei() {
  implementation(Dependencies.HUAWEI_COMPUTER_VISION_OCR)
  implementation(Dependencies.HUAWEI_COMPUTER_VISION_OCR_JK)
  implementation(Dependencies.HUAWEI_COMPUTER_VISION_OCR_LATIN)
  implementation(Dependencies.HUAWEI_COMPUTER_VISION_OCR_CN)
}


// huawei
fun DependencyHandler.Camerax() {
  implementation(Dependencies.CAMERA_CORE)
  implementation(Dependencies.CAMERA_CAMERA2)
  implementation(Dependencies.CAMERA_EXTENSIONS)
  implementation(Dependencies.CAMERA_VIDEO)
  implementation(Dependencies.CAMERA_VIEW)
  implementation(Dependencies.CAMERA_LIFECYCLE)
}

// huaweiFaceModels
fun DependencyHandler.HuaweiFaceModels() {
  implementation(Dependencies.HUAWEI_COMPUTER_VISION_FACE_)
  implementation(Dependencies.HUAWEI_COMPUTER_VISION_FACE_EMOTION_MODEL)
  implementation(Dependencies.HUAWEI_COMPUTER_VISION_FACE_FEATURE_MODEL)
  implementation(Dependencies.HUAWEI_COMPUTER_VISION_FACE_VISION_FACE_3D_MODEL)
  implementation(Dependencies.HUAWEI_COMPUTER_VISION_FACE_SHAPE_POINT_MODEL)
}

// compose ui dependecies
fun DependencyHandler.androidCompose() {
  implementation(Dependencies.ANDROID_CORE_KTS)
  implementation(Dependencies.ANDROID_LIFECYCLE_RUNTIME_KTX)
  implementation(Dependencies.ANDROIDX_ACTIVITY_COMPOSE)
  implementation(Dependencies.ANDROIDX_COMPOSE_UI)
  implementation(Dependencies.ANDROIDX_COMPOSE_UI_TOOLING_PREVIEW)
  implementation(Dependencies.ANDROIDX_COMPOSE_MATERIAL3)
}

fun DependencyHandler.implementation(depName: String) {
  add("implementation", depName)
}

private fun DependencyHandler.kapt(depName: String) {
  add("kapt", depName)
}

private fun DependencyHandler.compileOnly(depName: String) {
  add("compileOnly", depName)
}

private fun DependencyHandler.api(depName: String) {
  add("api", depName)
}

private fun DependencyHandler.debugImplementation(depName: String) {
  add("debugImplementation", depName)
}

private fun DependencyHandler.androidTestImplementation(depName: String) {
  add("androidTestImplementation", depName)
}

private fun DependencyHandler.testImplementation(depName: String) {
  add("testImplementation", depName)
}
