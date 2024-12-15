import org.jetbrains.kotlin.gradle.plugin.extraProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.duckytv"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.duckytv"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }

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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // To use Kotlin annotation processing tool (kapt) MUST HAVE!
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.51")

    // Dagger - Hilt
    kapt("com.google.dagger:hilt-android-compiler:2.51")
    kapt("androidx.hilt:hilt-compiler:1.2.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")

    //material icons - use with caution!
    // implementation "androidx.compose.material:material-icons-extended:$compose_version"
    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")

    // Coroutine Lifecycle Scopes
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")

    // JSON Converter
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // Exo player
    implementation ("androidx.media3:media3-exoplayer:1.2.1")
    implementation ("androidx.media3:media3-ui:1.2.1")
    implementation("androidx.media3:media3-exoplayer-hls:1.2.1")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.tv.foundation)
    implementation(libs.androidx.tv.material)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}