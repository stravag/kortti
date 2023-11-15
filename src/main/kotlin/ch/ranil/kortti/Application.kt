package ch.ranil.kortti

import ch.ranil.kortti.domain.CardRepository
import ch.ranil.kortti.domain.CardService
import ch.ranil.kortti.persistence.CardRepositoryImpl
import ch.ranil.kortti.web.configureController
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun main() {
    embeddedServer(
        factory = Netty,
        port = 9001,
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
        single<CardRepository> { CardRepositoryImpl() }
        single { CardService(get()) }
    }
