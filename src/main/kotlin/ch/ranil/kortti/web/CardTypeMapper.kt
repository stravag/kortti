package ch.ranil.kortti.web

import ch.ranil.kortti.domain.card.CardType
import ch.ranil.kortti.domain.card.CardType.*

object CardTypeMapper {
    @JvmStatic
    fun CardType.toEmoji() = when (this) {
        GENERIC -> "🤷‍♂️"
        BIRTHDAY -> "🥳"
        WEDDING -> "💍"
        SYMPATHY -> "❤️"
    }

    @JvmStatic
    fun CardType.toDisplayName() = when (this) {
        GENERIC -> "Generic"
        BIRTHDAY -> "Birthday"
        WEDDING -> "Wedding"
        SYMPATHY -> "Sympathy"
    }
}