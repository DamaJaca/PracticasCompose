buildscript {
    dependencies {
        classpath(libs.google.services)
        //Ktor
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.5.21")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false



    //Dagger Hilt
    id ("com.google.dagger.hilt.android") version "2.51.1" apply false

    kotlin("jvm") version "1.9.0" // or kotlin("multiplatform") or any other kotlin plugin
//    kotlin("plugin.serialization") version "2.0.0-RC3"
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
    alias(libs.plugins.google.gms.google.services) apply false
}