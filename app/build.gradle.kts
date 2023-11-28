plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.example.challengeempat"
    compileSdk = 34


    buildFeatures {
        viewBinding = true
        buildConfig = true
    }


    defaultConfig {
        applicationId = "com.example.challengeempat"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            buildConfigField(
                "STRING_FIELD_NAME",
                "BASE_URL",
                "https://testing.jasa-nikah-siri-amanah-profesional.com"
            )
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        create("staging") {
            buildConfigField(
                "STRING_FIELD_NAME",
                "BASE_URL",
                "https://testing.jasa-nikah-siri-amanah-profesional.com"
            )
        }
        create("production") {
            buildConfigField(
                "STRING_FIELD_NAME",
                "BASE_URL",
                "https://testing.jasa-nikah-siri-amanah-profesional.com"
            )
        }
    }



        compileOptions {
        /*    sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8*/

        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation("com.google.firebase:firebase-crashlytics-ktx:18.4.3")
    implementation("com.google.firebase:firebase-analytics-ktx:21.3.0")
    implementation("com.google.firebase:firebase-firestore-ktx:24.9.0")
    implementation("androidx.test.ext:junit-ktx:1.1.5")

    val nav_version = "2.5.1"
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview-selection:1.1.0")
    implementation("androidx.recyclerview:recyclerview:1.3.1")
    // live data
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    //Room
    implementation ("androidx.room:room-runtime:2.3.0")
    /*   implementation ("androidx.legacy:legacy-support-v4:1.0.0")*/
    kapt ("androidx.room:room-compiler:2.3.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.github.bumptech.glide:glide:4.16.0")


    implementation("com.google.firebase:firebase-auth-ktx:22.1.2")

    //Lottie
    implementation ("com.airbnb.android:lottie:5.0.3")

    //dependecy injection dagger hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    // Testing
    testImplementation("com.google.truth:truth:1.1.5")
    androidTestImplementation("com.google.truth:truth:1.1.5")
    androidTestImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.5.0")
    androidTestImplementation ("com.google.dagger:hilt-android-testing:2.48.1")
    testImplementation  ("com.google.dagger:hilt-android-testing:2.48.1")
}
