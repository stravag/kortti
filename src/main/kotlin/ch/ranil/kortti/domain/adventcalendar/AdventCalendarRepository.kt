package ch.ranil.kortti.domain.adventcalendar

interface AdventCalendarRepository {
    fun save(card: AdventCalendar)
    fun find(id: AdventCalendarId): AdventCalendar?
}