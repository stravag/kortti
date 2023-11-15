package ch.ranil.kortti.domain.adventcalendar

import ch.ranil.kortti.domain.Id
import ch.ranil.kortti.domain.IdFactory
import java.util.*

data class AdventCalendar(
    val id: AdventCalendarId,
)

@JvmInline
value class AdventCalendarId(override val value: UUID) : Id {
    companion object : IdFactory<AdventCalendarId>(AdventCalendarId::class)
}
