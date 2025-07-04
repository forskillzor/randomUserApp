plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
    kotlin("plugin.serialization") version "2.0.21"
    id("kotlin-parcelize")
}

android {
    namespace = "com.forskillzor.randomUserApp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.forskillzor.randomUserApp"
        minSdk = 24
        targetSdk = 35
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
            signingConfig = signingConfigs.getByName("debug")
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
        viewBinding = true
    }
}

dependencies {

    // Android core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit2.kotlinx.serialization.converter)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Glide
    implementation (libs.glide)

    // Swipe-to-refresh
    implementation(libs.androidx.swiperefreshlayout)


    // Testing

    // Kotlin Coroutines Testing
    testImplementation (libs.kotlinx.coroutines.test)

    // MockK
    testImplementation(libs.mockk)
    testImplementation(libs.mockk.agent.jvm)

    // Unit testing
    testImplementation(libs.junit)

    // Android testing
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    // Hilt Testing
    androidTestImplementation (libs.hilt.android.testing)
    kspAndroidTest (libs.hilt.android.compiler)
    testImplementation(kotlin("test"))

    // Turbine for stateFlow testing
    testImplementation (libs.turbine)

    // OkHttp3 mock web server
    androidTestImplementation(libs.mockwebserver)

    // serialization
    androidTestImplementation(libs.kotlinx.serialization.json)
    androidTestImplementation(libs.retrofit2.kotlinx.serialization.converter)
}