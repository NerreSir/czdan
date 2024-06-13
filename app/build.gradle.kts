plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

val composeVersion = "1.4.1"
val kotlinVersion = "1.8.0"

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mba.czdan"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        encoding = "UTF-8"
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }

    buildFeatures {
        compose = true
    }

    namespace = "com.mba.czdan"
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material3:material3:1.1.0-beta01")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.material:material-icons-core:$composeVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")
    implementation("androidx.compose.runtime:runtime-livedata:$composeVersion")
    implementation("androidx.compose.runtime:runtime-rxjava2:$composeVersion")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.25.1")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("androidx.compose.ui:ui:1.4.1")
    implementation("androidx.compose.foundation:foundation:1.4.1")
    implementation("androidx.compose.foundation:foundation-layout:1.4.1")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //DAGGER HİLT
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    kapt("com.google.dagger:hilt-compiler:2.44")
    implementation("com.google.dagger:hilt-android:2.44")
    //ROOM
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    // DatePicker
    implementation("androidx.compose.material3:material3:1.0.0-alpha01")
    //TABLAR ARASI KAYDIRARAK GEÇİŞ SAĞLAMA
    implementation("com.google.accompanist:accompanist-pager:0.26.1-alpha")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.26.1-alpha")
    implementation ("androidx.compose.material3:material3:1.1.0-beta01")
    implementation ("androidx.compose.material:material:1.4.1")
    //JSON CONVERTER
    implementation("org.jsoup:jsoup:1.14.3")
    //Number Picker
    implementation ("com.chargemap.compose:numberpicker:1.0.3")
}