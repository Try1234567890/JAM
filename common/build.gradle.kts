plugins {
    id("java-library")
    id("maven-publish")
}

version = "1.0.0"

dependencies {
    compileOnly("com.github.try:utilities:1.0.7")
    compileOnly("org.jetbrains:annotations:24.1.0")
}

