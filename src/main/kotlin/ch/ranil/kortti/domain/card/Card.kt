package ch.ranil.kortti.domain.card

import ch.ranil.kortti.domain.Id
import ch.ranil.kortti.domain.IdFactory
import java.time.LocalDateTime
import java.util.*

data class Card(
    val id: CardId,
    val entries: List<CardEntry> = emptyList()
)

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
