pluginManagement {
    val kotlin_version: String by settings
    val ktor_version: String by settings
    val jte_version: String by settings
    plugins {
        kotlin("jvm") version kotlin_version
        kotlin("plugin.serialization") version kotlin_version
        id("io.ktor.plugin") version ktor_version
        id("gg.jte.gradle") version jte_version
    }
}

rootProject.name = "kortti"
