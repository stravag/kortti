package ch.ranil.kortti.web.pages

import ch.ranil.kortti.web.STATIC_PATH
import ch.ranil.kortti.web.utils.htmx.hxBoost
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.util.pipeline.*
import kotlinx.html.*

suspend fun PipelineContext<Unit, ApplicationCall>.renderPage(
    title: String,
    content: BODY.() -> Unit = {}
) {
    call.respondHtml {
        head {
            title { +title }
            script { src = "$STATIC_PATH/htmx-1.9.8/htmx.min.js" }
        }
        body {
            hxBoost()
            content()
        }
    }
}