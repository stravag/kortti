package ch.ranil.kortti.domain.adventcalendar

interface AdventCalendarRepository {
    fun save(adventCalendar: AdventCalendar)
    fun find(id: AdventCalendarId): AdventCalendar?
}