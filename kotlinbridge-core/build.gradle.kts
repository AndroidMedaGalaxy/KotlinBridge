plugins {
    kotlin("jvm") version "1.9.22"
}

group = "com.androidmeda.kotlinbridge"
version = "0.1.0"

kotlin {
    jvmToolchain(17)
}

dependencies {
    testImplementation(kotlin("test"))
}
