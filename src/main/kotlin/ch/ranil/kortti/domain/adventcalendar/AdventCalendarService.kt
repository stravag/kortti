package ch.ranil.kortti.domain.adventcalendar

class AdventCalendarService(
    private val adventCalendarRepository: AdventCalendarRepository,
) {
    fun createAdventCalendar(): AdventCalendar {
        val calendar = AdventCalendar()
        adventCalendarRepository.save(calendar)
        return calendar
    }

    fun getAdventCalendar(adventCalendarId: AdventCalendarId): AdventCalendar {
        return adventCalendarRepository.getById(adventCalendarId)
    }

    fun changeDoorType(adventCalendarId: AdventCalendarId, doorNumber: Int, doorType: DoorType): AdventCalendar {
        val calendar = adventCalendarRepository.getById(adventCalendarId)
        calendar.changeDoorType(doorNumber, doorType)
        adventCalendarRepository.save(calendar)
        return calendar
    }

    fun editGifUrl(adventCalendarId: AdventCalendarId, doorNumber: Int, gifUrl: String?): AdventCalendar {
        val calendar = adventCalendarRepository.getById(adventCalendarId)
        calendar.editGifUrl(doorNumber, gifUrl)
        adventCalendarRepository.save(calendar)
        return calendar
    }

    fun openDoor(adventCalendarId: AdventCalendarId, doorNumber: Int): AdventCalendar {
        val calendar = adventCalendarRepository.getById(adventCalendarId)
        calendar.open(doorNumber)
        adventCalendarRepository.save(calendar)
        return calendar
    }

    fun publish(id: AdventCalendarId): AdventCalendar {
        val calendar = adventCalendarRepository.getById(id)
        calendar.publish()
        adventCalendarRepository.save(calendar)
        return calendar
    }
}
