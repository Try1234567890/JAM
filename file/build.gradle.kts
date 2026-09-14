plugins {
    id("java-library")
    id("maven-publish")
}


version = "5.0.0"

dependencies {
    implementation("com.github.try.jam:common:0.2.5")
    implementation("org.yaml:snakeyaml:2.6")
    implementation("com.google.code.gson:gson:2.14.0")
    implementation("tools.jackson.dataformat:jackson-dataformat-xml:3.2.1")
    implementation("tools.jackson.dataformat:jackson-dataformat-toml:3.2.1")
    implementation("tools.jackson.dataformat:jackson-dataformat-properties:3.2.1")

    testImplementation(platform("org.junit:junit-bom:5.11.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
