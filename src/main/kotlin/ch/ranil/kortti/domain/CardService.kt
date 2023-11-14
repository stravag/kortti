package ch.ranil.kortti.domain

class CardService(
    private val cardRepository: CardRepository,
) {
    fun createCard(): Card {
        val card = Card(
            id = CardId.random()
        )
        cardRepository.save(card)
        return card
    }

    fun findCard(cardId: CardId): Card? {
        return cardRepository.find(cardId)
    }
}
