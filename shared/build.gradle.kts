plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization") version "1.9.0"
    id("com.google.devtools.ksp") version "1.9.0-1.0.11"
}

kotlin {
    androidTarget()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = "1.0.0"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
        extraSpecAttributes["resources"] =
            "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.material)
                api(compose.materialIconsExtended)
                api(compose.foundation)
                api(compose.animation)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                //DI
                implementation("io.insert-koin:koin-core:3.4.2")
                implementation("io.insert-koin:koin-compose:1.0.3")

                // Image processing
                implementation("media.kamel:kamel-image:0.7.1")
                // Serialization
                implementation("io.ktor:ktor-client-core:2.3.3")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
                implementation("io.ktor:ktor-client-content-negotiation:2.3.3")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.3")
                // api mvvm-core, getViewModel for Compose Multiplatfrom
                api("dev.icerock.moko:mvvm-compose:0.16.1")
                // only ViewModel, EventsDispatcher, Dispatchers.UI
                api("dev.icerock.moko:mvvm-core:0.16.1")
                // Navigation
                api("moe.tlaster:precompose:1.5.0-beta02")
                // For ViewModel intergration
                api("moe.tlaster:precompose-viewmodel:1.5.0-beta02")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
                implementation("io.mockative:mockative:2.0.1")
            }
        }

        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.6.1")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.9.0")
                api(compose.preview)
                api(compose.uiTooling)
                //Serialization
                implementation("io.ktor:ktor-client-android:2.3.3")
                // Android ViewModel integration
                implementation("cafe.adriel.voyager:voyager-androidx:1.0.0-rc05")
                implementation("org.jetbrains.compose.ui:ui-tooling-preview:1.4.3")
            }
        }

        val androidUnitTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-darwin:2.3.3")
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.myapplication.common"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
    packagingOptions {
        resources.excludes.add("META-INF/*")
        resources.excludes.add("META-INF/DEPENDENCIES")
        resources.excludes.add("META-INF/versions")
    }
}
dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    configurations
        .filter { it.name.startsWith("ksp") && it.name.contains("Test") }
        .forEach {
            add(it.name, "io.mockative:mockative-processor:2.0.1")
        }
}
