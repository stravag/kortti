package ch.ranil.htmx

import ch.ranil.htmx.domain.AdventCalendarRepository
import ch.ranil.htmx.domain.AdventCalendarService
import ch.ranil.htmx.persistence.AdventCalendarRepositoryImpl
import ch.ranil.htmx.web.configureRouting
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
        configureRouting()
    }.start(wait = true)
}

private val appModule: Module
    get() = module {
        single<AdventCalendarRepository> { AdventCalendarRepositoryImpl() }
        single { AdventCalendarService(get()) }
    }
