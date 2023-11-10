package ch.ranil.htmx.domain

class AdventCalendarService(
    private val adventCalendarRepository: AdventCalendarRepository,
) {
    fun createCalendar() {
        adventCalendarRepository.save()
    }
}
