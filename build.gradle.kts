import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.gradleup.shadow") version "9.6.1" apply false
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "com.gradleup.shadow")

    val githubName = System.getenv("GITHUB_USERNAME")
    val githubToken = System.getenv("GITHUB_TOKEN")

    repositories {
        mavenCentral()
        maven {
            name = "Java-Libraries"
            url = uri("https://maven.pkg.github.com/$githubName/Java-Libraries")
            credentials {
                username = githubName
                password = githubToken
            }
        }
    }

    dependencies {
        "implementation"("com.github.try:utilities:1.0.7")

        "testImplementation"(platform("org.junit:junit-bom:6.1.3"))
        "testImplementation"("org.junit.jupiter:junit-jupiter")
        "testRuntimeOnly"("org.junit.platform:junit-platform-launcher")
    }

    configure<JavaPluginExtension> {
        withSourcesJar()
        withJavadocJar()
    }

    tasks.named<Test>("test") {
        useJUnitPlatform()
        failOnNoDiscoveredTests = false
    }

    tasks.named<ShadowJar>("shadowJar") {
        archiveBaseName.set(project.name)
        archiveClassifier.set("")
    }

    tasks.named<Jar>("jar") {
        enabled = false
    }

    tasks.named<Test>("test") {
        maxHeapSize = "4g"
    }

    configure<PublishingExtension> {
        publications {
            create<MavenPublication>("mavenJava") {
                from(components["shadow"])

                groupId = "com.github.try.jam"
                artifactId = project.name
                version = project.version.toString()
            }
        }

        repositories {
            maven {
                name = "Java-Libraries"
                url = uri("https://maven.pkg.github.com/$githubName/Java-Libraries")
                credentials {
                    username = githubName
                    password = githubToken
                }
            }
        }
    }
}