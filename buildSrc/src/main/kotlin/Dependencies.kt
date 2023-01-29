package com.simon.x_mlkit

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.extra


object Dependencies {

  const val ANDROID_CORE_KTS ="androidx.core:core-ktx:1.9.0"
  const val ANDROID_LIFECYCLE_RUNTIME_KTX ="androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"
  const val ANDROIDX_ACTIVITY_COMPOSE ="androidx.activity:activity-compose:1.6.1"
  const val ANDROIDX_COMPOSE_UI ="androidx.compose.ui:ui:${Versions.COMPOSE_VERSION}"
  const val ANDROIDX_COMPOSE_UI_TOOLING_PREVIEW ="androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE_VERSION}"
  const val ANDROIDX_COMPOSE_MATERIAL3 ="androidx.compose.material3:material3:1.1.0-alpha04"


  const val JUNIT ="junit:junit:4.13.2"
  const val ANDROIDX_JUNIT_TEST ="androidx.test.ext:junit:1.1.5"
  const val ESPRESSO_CORE ="androidx.test.espresso:espresso-core:3.5.1"
  const val ANDROIDX_COMPOSE_UI_TEST_JUNIT4 ="androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE_VERSION}"
  const val ANDROIDX_COMPOSE_UI_TOOLING ="androidx.compose.ui:ui-tooling:${Versions.COMPOSE_VERSION}"
  const val ANDROIDX_COMPOSE_UI_MANIFEST_TEST ="androidx.compose.ui:ui-test-manifest:${Versions.COMPOSE_VERSION}"

  // Import the base SDK.
  const val HUAWEI_COMPUTER_VISION_OCR = "com.huawei.hms:ml-computer-vision-ocr:${Versions.HUAWEI_VERSION}"
  const val HUAWEI_COMPUTER_VISION_OCR_LATIN = "com.huawei.hms:ml-computer-vision-ocr-latin-model:${Versions.HUAWEI_VERSION}"
  const val HUAWEI_COMPUTER_VISION_OCR_JK = "com.huawei.hms:ml-computer-vision-ocr-jk-model:${Versions.HUAWEI_VERSION}"
  const val HUAWEI_COMPUTER_VISION_OCR_CN = "com.huawei.hms:ml-computer-vision-ocr-cn-model:${Versions.HUAWEI_VERSION}"

  dependencies{
// Import the base SDK.
    implementation 'com.huawei.hms:ml-computer-vision-ocr:3.8.0.303'
    // Import the Latin-based language model package.
    implementation 'com.huawei.hms:ml-computer-vision-ocr-latin-model:3.8.0.303'
    // Import the Japanese and Korean model package.
    implementation 'com.huawei.hms:ml-computer-vision-ocr-jk-model:3.8.0.303'
    // Import the Chinese and English model package.
    implementation 'com.huawei.hms:ml-computer-vision-ocr-cn-model:3.8.0.303'
  }
}

//android test dependencies
fun DependencyHandler.androidTest(){
  testImplementation(Dependencies.JUNIT)
  androidTestImplementation(Dependencies.ANDROIDX_JUNIT_TEST)
  androidTestImplementation(Dependencies.ESPRESSO_CORE)
  androidTestImplementation(Dependencies.ANDROIDX_COMPOSE_UI_TEST_JUNIT4)
  debugImplementation(Dependencies.ANDROIDX_COMPOSE_UI_TOOLING)
  debugImplementation(Dependencies.ANDROIDX_COMPOSE_UI_MANIFEST_TEST)
}

//compose ui dependecies
fun DependencyHandler.androidCompose(){
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
}private fun DependencyHandler.testImplementation(depName: String) {
  add("testImplementation", depName)
}