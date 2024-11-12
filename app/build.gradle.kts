plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.block_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.block_app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation (libs.androidx.navigation.compose)

    implementation (libs.androidx.navigation.fragment)
    implementation (libs.androidx.navigation.ui)
    // Feature module support for Fragments
    implementation (libs.androidx.navigation.dynamic.features.fragment)
    // Testing Navigation
    androidTestImplementation (libs.androidx.navigation.testing)
    // Support for dynamic feature modules
    implementation (libs.androidx.graphics.shapes)

    // Retrofit for API requests
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    // ViewModel and LiveData for MVVM architecture
    implementation (libs.androidx.lifecycle.viewmodel.compose)
    implementation (libs.androidx.lifecycle.livedata)

    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    implementation (libs.logging.interceptor)
    implementation(libs.androidx.material.icons.extended)




}