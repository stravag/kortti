package ch.ranil.kortti.web.pages

import io.ktor.server.application.*
import kotlinx.html.*

suspend fun ApplicationCall.renderMainPage() {
    renderPage(title = "Kortti") {
        h1 { +"Kortti" }
        h2 { +"Create and share fun content" }
        section {
            h3 { +"Your place for fun online advent calendars" }
            p { +"" }
            form {
                action = "/advent-calendars"
                method = FormMethod.post
                button {
                    +"Create new advent calendar"
                }
            }
        }
        section {
            h3 { +"Your collaborative card generator" }
            p { +"Create a new card, share it with others, let them contribute and then deliver the greetings." }
            form {
                action = "/cards"
                method = FormMethod.post
                button {
                    +"Create new card"
                }
            }
        }
    }
}
