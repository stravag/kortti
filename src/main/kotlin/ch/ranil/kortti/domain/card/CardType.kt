package ch.ranil.kortti.domain.card

enum class CardType {
    GENERIC,
    BIRTHDAY,
    WEDDING,
    SYMPATHY;

    fun displayName() = when (this) {
        GENERIC -> "🤷"
        BIRTHDAY -> "🎂"
        WEDDING -> "💍"
        SYMPATHY -> "🖤"
    }
}