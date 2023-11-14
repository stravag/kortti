package ch.ranil.kortti.web.pages

import ch.ranil.kortti.web.STATIC_PATH
import ch.ranil.kortti.web.utils.hxBoost
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.util.pipeline.*
import kotlinx.html.*

suspend fun PipelineContext<Unit, ApplicationCall>.renderMainPage() {
    call.respondHtml {
        head { script { src = "$STATIC_PATH/htmx-1.9.8/htmx.min.js" } }
        body {
            h1 { +"Kortti" }
            h2 { +"Your collaborative card generator" }
            p { +"Create a new card, share it with others, let them contribute and then deliver the greetings." }
            form {
                action = "/card"
                method = FormMethod.post
                hxBoost()
                button {
                    +"Create new card"
                }
            }
        }
    }
}