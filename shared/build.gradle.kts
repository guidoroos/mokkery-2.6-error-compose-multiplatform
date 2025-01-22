import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)

    alias(libs.plugins.mokkery)
    alias(libs.plugins.kotlinComposeCompiler)
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.skie.configuration.annotations)
            implementation(libs.kotlin.coroutines)
            implementation(libs.androidx.compose.runtime)

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.test.kotlin.coroutines)
        }
    }
}

android {
    namespace = "com.viaplay.myapplicationskie"
    compileSdk = 35
    defaultConfig {
        minSdk = 29
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

kotlinArtifacts {
    Native.XCFramework("AchmeaCore") {
        targets(iosArm64, iosSimulatorArm64)
    }
}


//skie {
//    analytics {
//        disableUpload.set(true)
//        enabled.set(false)
//    }
//}
