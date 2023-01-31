@file:Suppress("UnstableApiUsage")

import Versions.COMPILE_SDK_VERSION
import Versions.MAX_SDK_VERSION
import Versions.MIN_SDK_VERSION
import com.simon.x_mlkit.Dependencies.COIL_COMPOSE
import com.simon.x_mlkit.androidCompose
import com.simon.x_mlkit.androidTest

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.simon.x_mlkit"
    compileSdk = COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = "com.simon.x_mlkit"
        minSdk = MIN_SDK_VERSION
        targetSdk = MAX_SDK_VERSION
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE_VERSION
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":facialRecognition"))
    implementation(project(":cameraXCompose"))
    androidCompose()
    androidTest()

    implementation(COIL_COMPOSE)

}