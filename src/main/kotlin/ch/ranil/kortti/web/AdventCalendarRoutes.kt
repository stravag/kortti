package ch.ranil.kortti.web

import ch.ranil.kortti.domain.adventcalendar.AdventCalendarId
import ch.ranil.kortti.domain.adventcalendar.AdventCalendarService
import io.ktor.server.application.*
import io.ktor.server.jte.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

const val ADVENT_CALENDAR_ID = "adventCalendarId"
const val DOOR_NUMBER = "doorNumber"

fun Routing.configureAdventCalendarRoutes() {

    val adventCalendarService by inject<AdventCalendarService>()

    post("/advent-calendars") {
        val adventCalendar = adventCalendarService.createAdventCalendar()
        call.respondRedirect303("/advent-calendars/${adventCalendar.id.value}")
    }

    get("/advent-calendars/{$ADVENT_CALENDAR_ID}") {
        val id = call.idPathParam<AdventCalendarId>(ADVENT_CALENDAR_ID)
        val calendar = adventCalendarService.getAdventCalendar(id)
        call.respond(JteContent("advent-calendar/advent-calendar.kte", mapOf("calendar" to calendar)))
    }

    put("/advent-calendars/{$ADVENT_CALENDAR_ID}/{$DOOR_NUMBER}") {
        val id = call.idPathParam<AdventCalendarId>(ADVENT_CALENDAR_ID)
        val doorNumber = requireNotNull(call.parameters[DOOR_NUMBER]).toInt()
        val calendar = adventCalendarService.openDoor(id, doorNumber)
        call.respond(JteContent("advent-calendar/door.kte", mapOf("calendar" to calendar, "doorNumber" to doorNumber)))
    }
}
