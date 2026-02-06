plugins {
    kotlin("jvm") version "2.2.20"
    id("org.jetbrains.kotlin.plugin.spring") version "2.3.0"
    id("org.springframework.boot") version "3.5.10"
    id("io.spring.dependency-management") version "1.1.7"
    id("gg.jte.gradle") version "3.2.2"
}

group = "ch.ranil"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(23)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("gg.jte:jte:3.2.2")
    jteGenerate("gg.jte:jte-models:3.2.2")

    implementation("org.webjars.npm:htmx.org:2.0.8")
    implementation("org.webjars.npm:htmx-ext-sse:2.2.4")
    implementation("org.webjars.npm:picocss__pico:2.1.1")
    implementation("org.webjars.npm:chart.js:4.5.1")

    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("com.microsoft.playwright:playwright:1.57.0")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

jte {
    generate()
    packageName = "ch.ranil.kortti.templates"
    jteExtension("gg.jte.models.generator.ModelExtension")
}

tasks.register<JavaExec>("playwright") {
    classpath(sourceSets["test"].runtimeClasspath)
    mainClass.set("com.microsoft.playwright.CLI")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
