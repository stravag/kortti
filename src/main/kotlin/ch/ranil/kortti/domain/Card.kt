package ch.ranil.kortti.domain

import java.util.UUID

data class Card(
    val id: CardId
)

@JvmInline
value class CardId(val value: UUID) {
    companion object {
        fun random(): CardId = CardId(UUID.randomUUID())
        fun parse(s: String): CardId = CardId(UUID.fromString(s))
    }
}
