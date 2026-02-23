plugins {
    kotlin("jvm") version "1.9.22"
    id("maven-publish")
}

group = "com.androidmeda.kotlinbridge"
version = "0.1.0"

kotlin {
    jvmToolchain(17)
}

// Create sources JAR
tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

// Create Javadoc JAR (using Dokka if available, fallback to standard javadoc)
tasks.register<Jar>("javadocJar") {
    archiveClassifier.set("javadoc")
    val javadocTask = tasks.findByName("dokkaHtml") ?: tasks.javadoc
    from(javadocTask)
}

publishing {
    publications {
        create<MavenPublication>("release") {
            from(components["java"])

            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/AndroidmedaGalaxy/KotlinBridge")

            credentials {
                username = project.findProperty("gpr.user") as String?
                    ?: System.getenv("GITHUB_ACTOR")
                password = project.findProperty("gpr.key") as String?
                    ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

dependencies {
    testImplementation(kotlin("test"))
}
