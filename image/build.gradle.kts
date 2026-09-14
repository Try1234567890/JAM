plugins {
    id("java-library")
    id("maven-publish")
}




dependencies {
    implementation("com.github.try.jam:common:0.2.5")
    implementation("com.github.try.jsf:colors:1.0.4")
    compileOnly("org.jetbrains:annotations:24.0.0")

}
