package ch.ranil.kortti.domain.card

import ch.ranil.kortti.domain.Id
import ch.ranil.kortti.domain.IdFactory
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random

class Card(
    val id: CardId,
    entries: List<CardEntry> = emptyList()
) {
    var entries: List<CardEntry> = entries
        private set

    fun addEntry() {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z')
        val randomString = (1..10)
            .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
            .joinToString("")

        val entry = CardEntry(
            id = CardEntryId.random(),
            dateTime = LocalDateTime.now(),
            text = "Hello $randomString"
        )

        entries += entry
    }

    fun deleteEntry(cardEntryId: CardEntryId) {
        entries = entries.filterNot { it.id == cardEntryId }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Card

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}

data class CardEntry(
    val id: CardEntryId,
    val dateTime: LocalDateTime,
    val text: String,
)

@JvmInline
value class CardId(override val value: UUID) : Id {
    companion object : IdFactory<CardId>(CardId::class)
}

@JvmInline
value class CardEntryId(override val value: UUID) : Id {
    companion object : IdFactory<CardEntryId>(CardEntryId::class)
}
