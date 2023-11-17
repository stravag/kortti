package ch.ranil.kortti

import ch.ranil.kortti.domain.EntityNotFoundException
import ch.ranil.kortti.domain.adventcalendar.AdventCalendarRepository
import ch.ranil.kortti.domain.adventcalendar.AdventCalendarService
import ch.ranil.kortti.domain.card.CardRepository
import ch.ranil.kortti.domain.card.CardService
import ch.ranil.kortti.persistence.AdventCalendarRepositoryImpl
import ch.ranil.kortti.persistence.CardRepositoryImpl
import ch.ranil.kortti.web.configureRoutes
import gg.jte.ContentType
import gg.jte.TemplateEngine
import gg.jte.generated.precompiled.DynamicTemplates
import gg.jte.generated.precompiled.Templates
import gg.jte.resolve.DirectoryCodeResolver
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.jte.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import java.nio.file.Path

fun main() {
    embeddedServer(
        factory = Netty, port = 9001, host = "0.0.0.0"
    ) {
        configureDependencyInjection()
        configureTemplating()
        configureErrorHandling()
        configureRoutes()
    }.start(wait = true)
}

private fun Application.configureErrorHandling() {
    install(StatusPages) {
        exception<EntityNotFoundException> { call, _ ->
            call.respond(JteContent("error/404.kte", emptyMap()))
        }
    }
}

private fun Application.configureTemplating() {
    install(Jte) {
        //val resolver = ResourceCodeResolver("templates", javaClass.classLoader)
        val resolver = DirectoryCodeResolver(Path.of("src/main/resources/templates"))
        templateEngine = TemplateEngine.create(resolver, ContentType.Html)
    }
}

private fun Application.configureDependencyInjection() {
    install(Koin) {
        modules(module {
            single<AdventCalendarRepository> { AdventCalendarRepositoryImpl() }
            single { AdventCalendarService(get()) }
            single<CardRepository> { CardRepositoryImpl() }
            single { CardService(get()) }
            single<Templates> {
                val resolver = DirectoryCodeResolver(Path.of("src/main/resources/templates"))
                val templateEngine = TemplateEngine.create(resolver, ContentType.Html)
                DynamicTemplates(templateEngine)
            }
        })
    }
}

