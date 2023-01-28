package com.simon.x_mlkit

object Dependencies {


    implementation()
    implementation("androidx.compose.ui:ui-tooling-preview:${rootProject.extra["compose_version"]}")
    implementation("androidx.compose.material3:material3:1.1.0-alpha04")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${rootProject.extra["compose_version"]}")
    debugImplementation("androidx.compose.ui:ui-tooling:${rootProject.extra["compose_version"]}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:${rootProject.extra["compose_version"]}")
  const val ANDROID_CORE_KTS ="androidx.core:core-ktx:1.9.0"
  const val ANDROID_LIFECYCLE_RUNTIME_KTX ="androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"
  const val ANDROIDX_ACTIVITY_COMPOSE ="androidx.activity:activity-compose:1.6.1"
  const val ANDROIDX_COMPOSE_UI ="androidx.compose.ui:ui:${Versions.COMPOSE_VERSION}"
  const val ANDROIDX ="androidx.compose.ui:ui-tooling-preview:${rootProject.extra["compose_version"]}"

}
