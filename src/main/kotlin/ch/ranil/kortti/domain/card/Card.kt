package ch.ranil.kortti.domain.card

import ch.ranil.kortti.domain.Id
import ch.ranil.kortti.domain.IdFactory
import java.time.LocalDateTime
import java.util.*

data class Card(
    val id: CardId,
    val type: CardType,
    val content: String,
    val createdAt: LocalDateTime,
)

enum class CardType {
    GENERIC,
    BIRTHDAY,
    WEDDING,
    SYMPATHY,
}

@JvmInline
value class CardId(override val value: UUID) : Id {
    companion object : IdFactory<CardId>(CardId::class)
}
