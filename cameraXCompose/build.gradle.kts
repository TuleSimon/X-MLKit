@file:Suppress("UnstableApiUsage")

import com.simon.x_mlkit.Camerax
import com.simon.x_mlkit.Dependencies.ACCOMPANIST_PERMISSIONS
import com.simon.x_mlkit.androidCompose
import com.simon.x_mlkit.androidTest

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.simon.cameraxcompose"
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE_VERSION
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    androidCompose()
    androidTest()
    Camerax()

}