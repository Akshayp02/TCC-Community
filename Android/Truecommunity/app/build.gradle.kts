plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}


android {
    namespace = "com.thecodingcult.truecommunity"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.thecodingcult.truecommunity"
        minSdk = 26
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
    implementation(libs.androidx.espresso.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.8.8")

    // System UI Controller
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.19.0")

    // Material Components
    implementation("androidx.compose.material:material:1.0.0")
    implementation("androidx.compose.material3:material3:1.2.0")

    // Image Loading
    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation("io.coil-kt:coil-compose:2.5.0")

    // Lottie
    implementation("com.airbnb.android:lottie:5.0.3")

    // Video and Audio Player
    implementation("androidx.compose.ui:ui:1.6.7")
    implementation("androidx.media3:media3-exoplayer:1.2.1")
    implementation("androidx.media3:media3-ui:1.2.1")

    //emoji library
    implementation("androidx.emoji2:emoji2:1.4.0")  // Core EmojiCompat
    implementation("androidx.emoji2:emoji2-bundled:1.4.0") // Bundled support


    // networking calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //swipe behaviour
    // In your build.gradle (if using Compose Foundation pager)
    implementation("androidx.compose.foundation:foundation:1.4.3")
    implementation("com.google.accompanist:accompanist-pager:0.30.1") // or latest
    implementation("com.google.accompanist:accompanist-pager-indicators:0.30.1")


}