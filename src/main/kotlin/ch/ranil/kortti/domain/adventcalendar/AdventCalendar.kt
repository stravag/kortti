package ch.ranil.kortti.domain.adventcalendar

import ch.ranil.kortti.domain.Id
import ch.ranil.kortti.domain.IdFactory
import java.util.*

class AdventCalendar(
    val id: AdventCalendarId,
    doors: List<Door>
) {
    var doors: List<Door> = doors
        private set

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

sealed interface Door {
    val closed: Boolean
}

class SimpleDoor(
    override val closed: Boolean,
) : Door

class GiphyDoor(
    override val closed: Boolean,
    val gifId: String
) : Door

@JvmInline
value class AdventCalendarId(override val value: UUID) : Id {
    companion object : IdFactory<AdventCalendarId>(AdventCalendarId::class)
}
