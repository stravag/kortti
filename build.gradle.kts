plugins {
    kotlin("jvm") version "2.3.0"
    id("org.jetbrains.kotlin.plugin.spring") version "2.3.0"
    id("org.springframework.boot") version "3.5.10"
    id("io.spring.dependency-management") version "1.1.7"
    id("gg.jte.gradle") version "3.2.2"
}

group = "ch.ranil"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("gg.jte:jte:3.2.2")
    implementation("gg.jte:jte-runtime:3.2.2")
    implementation("gg.jte:jte-kotlin:3.2.2")
    jteGenerate("gg.jte:jte-models:3.2.2")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

jte {
    precompile()
    generate()
    contentType = gg.jte.ContentType.Html
    packageName = "ch.ranil.kortti.templates"
    jteExtension("gg.jte.models.generator.ModelExtension") {
        property("language", "Kotlin")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
