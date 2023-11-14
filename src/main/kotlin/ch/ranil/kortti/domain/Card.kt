package ch.ranil.kortti.domain

import java.time.LocalDateTime
import java.util.UUID

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
value class CardId(val value: UUID) {
    companion object {
        fun random(): CardId = CardId(UUID.randomUUID())
        fun parse(s: String): CardId = CardId(UUID.fromString(s))
    }
}

@JvmInline
value class CardEntryId(val value: UUID) {
    companion object {
        fun random(): CardEntryId = CardEntryId(UUID.randomUUID())
        fun parse(s: String): CardEntryId = CardEntryId(UUID.fromString(s))
    }
}
