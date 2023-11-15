package ch.ranil.kortti.web

import ch.ranil.kortti.domain.adventcalendar.AdventCalendarService
import ch.ranil.kortti.web.utils.respondRedirect303
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.html.body
import kotlinx.html.h1
import org.koin.ktor.ext.inject

const val ADVENT_CALENDAR_ID = "adventCalendarId"

fun Routing.configureAdventCalendarRoutes() {

    val adventCalendarService by inject<AdventCalendarService>()

    post("/advent-calendars") {
        val adventCalendar = adventCalendarService.createAdventCalendar()
        call.respondRedirect303("/advent-calendars/${adventCalendar.id.value}")
    }

    get("/advent-calendars/{$ADVENT_CALENDAR_ID}") {
        call.respondHtml { body { h1 { +"TODO :-)" } } }
    }
}
