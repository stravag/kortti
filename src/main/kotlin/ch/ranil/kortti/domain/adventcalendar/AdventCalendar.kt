package ch.ranil.kortti.domain.adventcalendar

import ch.ranil.kortti.domain.Id
import ch.ranil.kortti.domain.IdFactory
import org.slf4j.LoggerFactory
import java.util.*

class AdventCalendar(
    val id: AdventCalendarId = AdventCalendarId.random(),
    published: Boolean = false,
    private var mutableDoors: MutableList<Door> = MutableList(24) { SimpleDoor(closed = true) }
) {
    var published: Boolean = published
        private set

    val doors: List<Door>
        get() = mutableDoors.toList()

    fun changeDoorType(doorNumber: Int, type: DoorType) {
        if (!published && isValidDoorNumber(doorNumber)) {
            mutableDoors[doorNumber] = type.builder.invoke()
        } else {
            LOGGER.warn("Cannot change type of door #$doorNumber in $this")
        }
    }

    fun publish() {
        published = true
    }

    fun open(doorNumber: Int) {
        if (published && isValidDoorNumber(doorNumber)) {
            val door = mutableDoors[doorNumber]
            door.closed = false
        } else {
            LOGGER.warn("Cannot open door #$doorNumber in $this")
        }
    }

    private fun isValidDoorNumber(doorNumber: Int): Boolean {
        return mutableDoors.indices.contains(doorNumber)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AdventCalendar

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(AdventCalendar::class.java)
    }
}

sealed interface Door {
    var closed: Boolean
}

class SimpleDoor(
    override var closed: Boolean = true,
) : Door

class GiphyDoor(
    override var closed: Boolean = true,
    val gifId: String? = null,
) : Door

enum class DoorType(val builder: () -> Door) {
    SIMPLE({ SimpleDoor() }),
    GIPHY({ GiphyDoor() }),
}

@JvmInline
value class AdventCalendarId(override val value: UUID) : Id {
    companion object : IdFactory<AdventCalendarId>(AdventCalendarId::class)
}
