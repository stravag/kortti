package ch.ranil.kortti.domain

class AdventCalendarService(
    private val adventCalendarRepository: AdventCalendarRepository,
) {
    fun createCalendar() {
        adventCalendarRepository.save()
    }
}
