package ch.ranil.kortti.web.pages

import io.ktor.server.application.*
import io.ktor.util.pipeline.*
import kotlinx.html.*

suspend fun PipelineContext<Unit, ApplicationCall>.renderMainPage() {
    renderPage(title = "Kortti") {
        h1 { +"Kortti" }
        h2 { +"Your collaborative card generator" }
        p { +"Create a new card, share it with others, let them contribute and then deliver the greetings." }
        form {
            action = "/card"
            method = FormMethod.post
            button {
                +"Create new card"
            }
        }
    }
}
