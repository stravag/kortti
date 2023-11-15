package ch.ranil.kortti.domain.card

interface CardRepository {
    fun save(card: Card)
    fun find(id: CardId): Card?
}