// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.devtools.ksp") version "2.2.0-2.0.2" apply false
    id("com.google.dagger.hilt.android") version "2.56.2" apply false
}
buildscript {
    repositories {
        google()
    }
    dependencies {
        val nav_version = "2.9.0"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
        classpath ("com.android.tools.build:gradle:8.10.0")
    }
}