package ch.ranil.kortti.web

import ch.ranil.kortti.domain.adventcalendar.AdventCalendarId
import ch.ranil.kortti.domain.adventcalendar.AdventCalendarService
import io.ktor.server.application.*
import io.ktor.server.jte.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

const val ADVENT_CALENDAR_ID = "adventCalendarId"

fun Routing.configureAdventCalendarRoutes() {

    val adventCalendarService by inject<AdventCalendarService>()

    post("/advent-calendars") {
        val adventCalendar = adventCalendarService.createAdventCalendar()
        call.respondRedirect303("/advent-calendars/${adventCalendar.id.value}")
    }

    get("/advent-calendars/{$ADVENT_CALENDAR_ID}") {
        val id = call.idPathParam<AdventCalendarId>(ADVENT_CALENDAR_ID)
        val calendar = adventCalendarService.getAdventCalendar(id)
        call.respond(JteContent("adventCalendar.kte", mapOf("calendar" to calendar)))
    }
}
