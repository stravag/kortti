import kotlin.io.path.Path

val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.9.20"
    kotlin("plugin.serialization") version "1.9.20"
    id("io.ktor.plugin") version "2.3.6"
    id("gg.jte.gradle") version "3.1.4"
}

group = "ch.ranil"
version = "0.0.1"

application {
    mainClass.set("ch.ranil.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-host-common-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")

    implementation("io.ktor:ktor-server-status-pages")
    implementation("io.ktor:ktor-server-jte")

    implementation("gg.jte:jte:3.1.4")
    implementation("gg.jte:jte-runtime:3.1.4")
    implementation("gg.jte:jte-kotlin:3.1.4")
    jteGenerate("gg.jte:jte-models:3.1.4")

    implementation("io.ktor:ktor-client-java")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.1")

    implementation("ch.qos.logback:logback-classic:$logback_version")

    implementation("io.insert-koin:koin-ktor:3.5.1")

    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

kotlin {
    jvmToolchain(17)
}

jte {
    generate()
    sourceDirectory.set(Path("src/main/resources/templates"))
    binaryStaticContent.set(true)
    jteExtension("gg.jte.models.generator.ModelExtension") {
        property("language", "Kotlin")
    }
}
