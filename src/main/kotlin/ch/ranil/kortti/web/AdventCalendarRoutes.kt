package ch.ranil.kortti.web

import ch.ranil.kortti.domain.adventcalendar.AdventCalendarId
import ch.ranil.kortti.domain.adventcalendar.AdventCalendarService
import ch.ranil.kortti.domain.adventcalendar.DoorType
import gg.jte.generated.precompiled.Templates
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

const val ADVENT_CALENDAR_ID = "adventCalendarId"
const val DOOR_NUMBER = "doorNumber"

fun Routing.configureAdventCalendarRoutes() {

    val templates by inject<Templates>()
    val adventCalendarService by inject<AdventCalendarService>()

    post("/advent-calendars") {
        val adventCalendar = adventCalendarService.createAdventCalendar()
        call.respondRedirect303("/advent-calendars/${adventCalendar.id.value}")
    }

    get("/advent-calendars/{$ADVENT_CALENDAR_ID}") {
        val id = call.idPathParam<AdventCalendarId>(ADVENT_CALENDAR_ID)
        val calendar = adventCalendarService.getAdventCalendar(id)
        call.respondTemplate {
            if (calendar.published) {
                templates.adventCalendarViewPage(calendar)
            } else {
                templates.adventCalendarEditPage(calendar)
            }
        }
    }

    put("/advent-calendars/{$ADVENT_CALENDAR_ID}/{$DOOR_NUMBER}") {
        val id = call.idPathParam<AdventCalendarId>(ADVENT_CALENDAR_ID)
        val doorNumber = requireNotNull(call.parameters[DOOR_NUMBER]).toInt()
        val calendar = adventCalendarService.openDoor(id, doorNumber)
        call.respondTemplate { templates.adventCalendarDoorFragment(doorNumber, calendar) }
    }

    put("/advent-calendars/{$ADVENT_CALENDAR_ID}/{$DOOR_NUMBER}/edit") {
        val id = call.idPathParam<AdventCalendarId>(ADVENT_CALENDAR_ID)
        val doorNumber = requireNotNull(call.parameters[DOOR_NUMBER]).toInt()
        val type = DoorType.valueOf(requireNotNull(call.receiveParameters()["type"]))
        val calendar = adventCalendarService.changeDoorType(id, doorNumber, type)
        call.respondTemplate { templates.adventCalendarEditPage(calendar) }
    }
}
