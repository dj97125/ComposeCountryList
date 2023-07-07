buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.46.1")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.8.22")

    }

}

plugins {
    id("com.android.application").version("7.4.0").apply(false)
    id("com.android.library").version("7.4.0").apply(false)
    id("com.google.dagger.hilt.android").version("2.46.1").apply(false)
    kotlin("android").version("1.7.0").apply(false)
}


tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}
