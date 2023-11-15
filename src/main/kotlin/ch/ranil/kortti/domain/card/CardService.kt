package ch.ranil.kortti.domain.card

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

    fun addEntryToCard(cardId: CardId): Card {
        val card = requireNotNull(cardRepository.find(cardId))
        card.addEntry()
        cardRepository.save(card)
        return card
    }

    fun deleteEntryFromCard(cardId: CardId, cardEntryId: CardEntryId): Card {
        val card = requireNotNull(cardRepository.find(cardId))
        card.deleteEntry(cardEntryId)
        cardRepository.save(card)
        return card
    }
}
