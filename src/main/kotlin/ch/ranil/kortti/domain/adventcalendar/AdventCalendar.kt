package ch.ranil.kortti.domain.adventcalendar

import ch.ranil.kortti.domain.Id
import ch.ranil.kortti.domain.IdFactory
import java.util.*

class AdventCalendar(
    val id: AdventCalendarId,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AdventCalendar

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}

@JvmInline
value class AdventCalendarId(override val value: UUID) : Id {
    companion object : IdFactory<AdventCalendarId>(AdventCalendarId::class)
}
