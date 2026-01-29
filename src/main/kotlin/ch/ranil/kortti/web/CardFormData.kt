package ch.ranil.kortti.web

import ch.ranil.kortti.domain.card.CardType

data class CardFormData(
    val content: String,
    val type: CardType,
)