import com.simon.x_mlkit.Dependencies.COIL_COMPOSE
import com.simon.x_mlkit.Dependencies.KOTLINX_COROUTINES
import com.simon.x_mlkit.Dependencies.KOTLINX_COROUTINES_ANDROID
import com.simon.x_mlkit.Dependencies.KOTLINX_COROUTINES_PLAY_SERVICES
import com.simon.x_mlkit.Huawei
import com.simon.x_mlkit.HuaweiFaceModels

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.huawei.agconnect")
}

android {
    namespace = "com.simon.x_mlkit.facialrecognition"
    compileSdk = Versions.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = Versions.MIN_SDK_VERSION
        targetSdk = Versions.MAX_SDK_VERSION

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    Huawei()
//    // Import the base SDK.
//    implementation("com.huawei.hms:ml-computer-vision-face:3.7.0.301")
//// Import the contour and key point detection model package.
//    implementation("com.huawei.hms:ml-computer-vision-face-shape-point-model:3.7.0.301")
//// Import the facial expression detection model package.
//    implementation("com.huawei.hms:ml-computer-vision-face-emotion-model:3.7.0.301")
//// Import the facial feature detection model package.
//    implementation("com.huawei.hms:ml-computer-vision-face-feature-model:3.7.0.301")
//    // Import the 3D face detection model package.
//    implementation("com.huawei.hms:ml-computer-vision-face-3d-model:3.7.0.301")
    implementation(KOTLINX_COROUTINES)
    implementation(KOTLINX_COROUTINES_ANDROID)
    implementation(KOTLINX_COROUTINES_PLAY_SERVICES)
    implementation(COIL_COMPOSE)
}