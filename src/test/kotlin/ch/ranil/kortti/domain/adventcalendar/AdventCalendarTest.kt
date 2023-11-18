package ch.ranil.kortti.domain.adventcalendar

import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AdventCalendarTest {

    @Test
    fun `can change type of door`() {
        val calendar = AdventCalendar()
        assertFalse {
            calendar.doors.any { it is GiphyDoor }
        }

        calendar.changeDoorType(0, DoorType.GIPHY)
        calendar.changeDoorType(23, DoorType.GIPHY)
        assertTrue {
            calendar.doors.first() is GiphyDoor
            calendar.doors.last() is GiphyDoor
        }
    }

    @Test
    fun `change of non existent door type doesn't crash`() {
        // arrange
        val calendar = AdventCalendar()
        assertFalse {
            calendar.doors.any { it is GiphyDoor }
        }

        // act
        calendar.changeDoorType(-1, DoorType.GIPHY)
        calendar.changeDoorType(24, DoorType.GIPHY)

        // assert
        assertFalse {
            calendar.doors.any { it is GiphyDoor }
        }
    }

    @Test
    fun `can't door type of published advent calendar`() {
        // arrange
        val calendar = AdventCalendar(published = true)
        assertFalse {
            calendar.doors.any { it is GiphyDoor }
        }

        // act
        calendar.changeDoorType(0, DoorType.GIPHY)

        // assert
        assertFalse {
            calendar.doors.any { it is GiphyDoor }
        }
    }

    @Test
    fun publish() {
        // arrange
        val calendar = AdventCalendar()
        assertFalse { calendar.published }

        // act
        calendar.publish()

        // assert
        assertTrue { calendar.published }
    }
}