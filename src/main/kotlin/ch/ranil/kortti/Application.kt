package ch.ranil.kortti

import ch.ranil.kortti.domain.EntityNotFoundException
import ch.ranil.kortti.domain.adventcalendar.AdventCalendarRepository
import ch.ranil.kortti.domain.adventcalendar.AdventCalendarService
import ch.ranil.kortti.domain.card.CardRepository
import ch.ranil.kortti.domain.card.CardService
import ch.ranil.kortti.persistence.AdventCalendarRepositoryImpl
import ch.ranil.kortti.persistence.CardRepositoryImpl
import ch.ranil.kortti.web.configureRoutes
import gg.jte.TemplateEngine
import gg.jte.resolve.ResourceCodeResolver
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.jte.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
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
        install(Jte) {
            val resolver = ResourceCodeResolver("templates", javaClass.classLoader)
            templateEngine = TemplateEngine.create(resolver, gg.jte.ContentType.Html)
        }
        install(StatusPages) {
            exception<EntityNotFoundException> { call, _ ->
                call.respond(JteContent("error/404.kte", emptyMap()))
            }
        }
        configureRoutes()
    }.start(wait = true)
}

private val appModule: Module
    get() = module {
        single<AdventCalendarRepository> { AdventCalendarRepositoryImpl() }
        single { AdventCalendarService(get()) }
        single<CardRepository> { CardRepositoryImpl() }
        single { CardService(get()) }
    }
