import kotlin.io.path.Path

val kotlin_version: String by project
val ktor_version: String by project
val koin_version: String by project
val jte_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("io.ktor.plugin")
    id("gg.jte.gradle")
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

    implementation("gg.jte:jte:$jte_version")
    implementation("gg.jte:jte-runtime:$jte_version")
    implementation("gg.jte:jte-kotlin:$jte_version")
    jteGenerate("gg.jte:jte-models:$jte_version")

    implementation("io.ktor:ktor-client-java:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    implementation("ch.qos.logback:logback-classic:$logback_version")

    implementation("io.insert-koin:koin-ktor:$koin_version")

    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
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
