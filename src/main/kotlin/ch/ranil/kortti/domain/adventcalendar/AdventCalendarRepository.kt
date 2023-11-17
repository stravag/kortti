package ch.ranil.kortti.domain.adventcalendar

interface AdventCalendarRepository {
    fun save(adventCalendar: AdventCalendar)
    fun getById(id: AdventCalendarId): AdventCalendar
}