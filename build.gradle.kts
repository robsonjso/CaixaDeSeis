// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        // Check that you have Google's Maven repository (if not, add it).
        mavenCentral()
        google()
    }
    dependencies {
        classpath("com.google.gms:google-services:4.4.2")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false
    alias(libs.plugins.google.firebase.crashlytics) apply false

}
