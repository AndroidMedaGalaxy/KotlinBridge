plugins {
    id("com.android.application") version "8.2.2"
    kotlin("android") version "1.9.22"
}

android {
    namespace = "com.androidmeda.kotlinbridge.sample"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.androidmeda.kotlinbridge.sample"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":kotlinbridge-core"))
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
}
