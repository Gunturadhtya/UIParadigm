plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.experiment.uiparadigm"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.experiment.uiparadigm"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlinOptions {
        jvmTarget = "21"
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    // ─── Source Sets ───────────────────────────────────────────────
    sourceSets {
        getByName("main") {
            java.srcDirs("src/main/java")
            manifest.srcFile("src/main/AndroidManifest.xml")
        }
    }

    // Build variants: one for each paradigm
    flavorDimensions += "paradigm"
    productFlavors {
        create("imperative") {
            dimension = "paradigm"
            applicationIdSuffix = ".imperative"
            versionNameSuffix = "-imperative"
        }
        create("declarative") {
            dimension = "paradigm"
            applicationIdSuffix = ".declarative"
            versionNameSuffix = "-declarative"
        }
    }
}

dependencies {
    // Shared
    implementation(libs.androidx.core.ktx)

    // Imperative only
    "imperativeImplementation"(libs.androidx.appcompat)
    "imperativeImplementation"(libs.material)
    "imperativeImplementation"(libs.androidx.recyclerview)

    // Declarative only
    "declarativeImplementation"(libs.androidx.activity.compose)
    "declarativeImplementation"(platform(libs.androidx.compose.bom))
    "declarativeImplementation"(libs.androidx.ui)
    "declarativeImplementation"(libs.androidx.ui.graphics)
    "declarativeImplementation"(libs.androidx.ui.tooling.preview)
    "declarativeImplementation"(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)
}
