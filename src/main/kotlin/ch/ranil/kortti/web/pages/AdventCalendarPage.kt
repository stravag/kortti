package ch.ranil.kortti.web.pages

import ch.ranil.kortti.domain.adventcalendar.AdventCalendar
import ch.ranil.kortti.domain.adventcalendar.AdventCalendarId
import ch.ranil.kortti.domain.adventcalendar.Door
import io.ktor.server.application.*
import kotlinx.html.*

suspend fun ApplicationCall.renderAdventCalendarPage(adventCalendar: AdventCalendar) {
    renderPage(adventCalendar.id.value.toString()) {
        h1 { +"Advent Calendar" }
        p { +"${adventCalendar.id}" }
        ul {
            id = "entries"
            adventCalendar.doors
                .shuffled()
                .forEachIndexed { i, door ->
                    renderDoor(adventCalendar.id, i, door)
                }
        }
    }
}

suspend fun ApplicationCall.renderMissingAdventCalendarPage() {
    renderPage("Advent Calendar Not Found") {
        h1 { +"Advent Calendar Not Found" }
    }
}

fun UL.renderDoor(adventCalendarId: AdventCalendarId, num: Int, door: Door) {
    li {
        p { +"$door $num" }
    }
}
