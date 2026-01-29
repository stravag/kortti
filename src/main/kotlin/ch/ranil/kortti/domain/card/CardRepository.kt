package ch.ranil.kortti.domain.card

interface CardRepository {
    fun save(card: Card)
    fun getAll(): List<Card>
    fun delete(id: CardId)
}