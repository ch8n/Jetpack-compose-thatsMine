// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    ext {
        compose_version = "1.0.0-alpha06"
    }
    ext.kotlin_version = "1.4.10"
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.2.0-alpha15")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        // for proto-dataStore
        classpath("com.google.protobuf:protobuf-gradle-plugin:0.8.12")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}