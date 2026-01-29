package ch.ranil.kortti.domain.card

import java.time.LocalDateTime

data class Card(
    val id: CardId,
    val type: CardType,
    val content: String,
    val createdAt: LocalDateTime,
)

