package ch.ranil.kortti.persistence

import ch.ranil.kortti.domain.adventcalendar.AdventCalendar
import ch.ranil.kortti.domain.adventcalendar.AdventCalendarId
import ch.ranil.kortti.domain.adventcalendar.AdventCalendarRepository
import org.slf4j.LoggerFactory

class AdventCalendarRepositoryImpl : AdventCalendarRepository {
    private val calendars = mutableMapOf<AdventCalendarId, AdventCalendar>()

    override fun save(adventCalendar: AdventCalendar) {
        calendars[adventCalendar.id] = adventCalendar
        LOGGER.info("AdventCalendar saved: $adventCalendar")
    }

    override fun find(id: AdventCalendarId): AdventCalendar? {
        return calendars[id]
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(AdventCalendarRepositoryImpl::class.java)
    }
}
