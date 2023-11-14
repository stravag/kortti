package ch.ranil.kortti

import ch.ranil.kortti.domain.AdventCalendarRepository
import ch.ranil.kortti.domain.AdventCalendarService
import ch.ranil.kortti.persistence.AdventCalendarRepositoryImpl
import ch.ranil.kortti.controller.configureController
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun main() {
    embeddedServer(
        factory = Netty,
        port = 8080,
        host = "0.0.0.0"
    ) {
        install(Koin) {
            modules(appModule)
        }
        configureController()
    }.start(wait = true)
}

private val appModule: Module
    get() = module {
        single<AdventCalendarRepository> { AdventCalendarRepositoryImpl() }
        single { AdventCalendarService(get()) }
    }
