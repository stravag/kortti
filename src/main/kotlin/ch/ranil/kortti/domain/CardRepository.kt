package ch.ranil.kortti.domain

interface CardRepository {
    fun save(card: Card)
    fun find(id: CardId): Card?
}