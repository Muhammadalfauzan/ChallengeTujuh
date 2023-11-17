/*
buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
        classpath ("com.android.tools.build:gradle:3.4.3")
    }
}
*/


buildscript {

    dependencies {

        classpath("com.google.gms:google-services:4.4.0")
        classpath("com.android.tools.build:gradle:7.3.0")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.9")
    }
}

plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    id("com.android.library") version "8.1.0" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    /*id("com.google.gms.google-services") version "4.4.0" apply false*/
}


