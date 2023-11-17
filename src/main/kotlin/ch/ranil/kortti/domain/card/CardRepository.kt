package ch.ranil.kortti.domain.card

interface CardRepository {
    fun save(card: Card)
    fun getById(id: CardId): Card
}