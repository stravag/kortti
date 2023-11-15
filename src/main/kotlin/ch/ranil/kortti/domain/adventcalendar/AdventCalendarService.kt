package ch.ranil.kortti.domain.adventcalendar

class AdventCalendarService(
    private val adventCalendarRepository: AdventCalendarRepository,
) {
    fun createAdventCalendar(): AdventCalendar {
        val card = AdventCalendar(
            id = AdventCalendarId.random()
        )
        adventCalendarRepository.save(card)
        return card
    }
}
