package ch.ranil.kortti.web.pages

import ch.ranil.kortti.domain.Card
import ch.ranil.kortti.web.STATIC_PATH
import ch.ranil.kortti.web.utils.hxPost
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.util.pipeline.*
import kotlinx.html.*

suspend fun PipelineContext<Unit, ApplicationCall>.renderCardPage(card: Card?) {
    call.respondHtml {
        head { script { src = "$STATIC_PATH/htmx-1.9.8/htmx.min.js" } }
        body {
            if (card == null) {
                h1 { +"Card Not Found" }
            } else {
                h1 { +"Card" }
                p { +"$card" }
            }
        }
    }
}
