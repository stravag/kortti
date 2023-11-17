package ch.ranil.kortti.domain.adventcalendar

class AdventCalendarService(
    private val adventCalendarRepository: AdventCalendarRepository,
) {
    fun createAdventCalendar(): AdventCalendar {
        val calendar = AdventCalendar(
            id = AdventCalendarId.random(),
            doors = List(24) { SimpleDoor(closed = true) }
        )
        adventCalendarRepository.save(calendar)
        return calendar
    }

    fun getAdventCalendar(adventCalendarId: AdventCalendarId): AdventCalendar {
        return adventCalendarRepository.getById(adventCalendarId)
    }
}
