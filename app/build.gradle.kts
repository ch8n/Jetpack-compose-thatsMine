plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.protobuf") version ("0.8.13")// adding support for protobuf
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.2")

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    defaultConfig {
        applicationId = "com.ch8n.thatsmine"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-alpha06"
        kotlinCompilerVersion = "1.4.10"
    }


}

// adding support for protobuf
protobuf {

    protoc {
        artifact = 'com.google.protobuf:protoc:3.7.0'
    }

    plugins {
        javalite {
            artifact = "com.google.protobuf:protoc-gen-javalite:3.0.0"
        }
    }
    generateProtoTasks {
        all().each { task ->
            task.builtins {
                remove java
            }
            task.plugins {
                javalite { }
            }
        }
    }
}


sourceSets {
    main.java.srcDirs += "${protobuf.generatedFilesBaseDir}/main/javalite"
    main.java.srcDirs += "$projectDir/src/main/proto"
}

processResources {
    exclude("**/*.proto")
}


dependencies {

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.10")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.compose.ui:ui:1.0.0-alpha06")
    implementation("androidx.compose.material:material:1.0.0-alpha06")
    implementation("androidx.ui:ui-tooling:1.0.0-alpha06")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.0-beta01")


    // coroutines and flow
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.0")


    //protobuf
    implementation("com.google.protobuf:protobuf-lite:3.0.1")

    // datastore
    implementation("androidx.datastore:datastore-core:1.0.0-alpha01") //protobuf datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0-alpha02")

    // logging
    implementation("com.jakewharton.timber:timber:4.7.1")

    testImplementation("junit:junit:4.13.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

    val room_version = "2.2.5"
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    //Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")
    //room testing
    testImplementation("androidx.room:room-testing:$room_version")


    // test
    testImplementation("androidx.test:core:1.3.0")
    testImplementation("androidx.test:core-ktx:1.3.0")
    testImplementation("org.robolectric:robolectric:4.5-alpha-2")

    //coroutines
    val coroutinesVersion = "1.4.0"
    //testing coroutines
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")

    // AndroidJUnitRunner and JUnit Rules
    testImplementation("androidx.test:runner:1.3.0")
    testImplementation("androidx.test:rules:1.3.0")

    // Assertions
    testImplementation("androidx.test.ext:junit:1.1.2")
    testImplementation("androidx.test.ext:truth:1.3.0")
    testImplementation("com.google.truth:truth:1.1")


    // facker for mock data
    implementation("com.github.javafaker:javafaker:1.0.2")
    testImplementation("io.mockk:mockk:1.10.2")
}