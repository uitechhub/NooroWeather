import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kapt)
    alias(libs.plugins.protobuf)
}

val apiKey: String = project.findProperty("API_KEY") as String? ?: ""

android {
    namespace = "com.ivantest.nooroweather"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.ivantest.nooroweather"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        val key: String = gradleLocalProperties(rootDir, providers).getProperty("weatherApiKey")
        debug {
            buildConfigField("String", "WEATHER_API_KEY", "\"$key\"")
        }
        release {
            buildConfigField("String", "WEATHER_API_KEY", "\"$key\"")
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
        buildConfig = true
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
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Hilt for Dependency Injection
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Retrofit for API Calls
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    // coil
    implementation(libs.coil.compose)

    // DataStore for Preferences
    implementation(libs.androidx.datastore.preferences)

    // Logging (optional for debugging network requests)
    implementation(libs.logging.interceptor)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

kapt {
    correctErrorTypes = true
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.12"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}