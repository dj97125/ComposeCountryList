buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.0.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.42")

    }

}

plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.4.0").apply(false)
    id("com.android.library").version("7.4.0").apply(false)
    id("com.google.dagger.hilt.android").version("2.42").apply(false)
    kotlin("android").version("1.7.0").apply(false)
}


tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}
