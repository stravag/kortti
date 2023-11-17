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

    fun findCard(cardId: CardId): Card {
        return cardRepository.getById(cardId)
    }

    fun addEntryToCard(cardId: CardId): Card {
        val card = cardRepository.getById(cardId)
        card.addEntry()
        cardRepository.save(card)
        return card
    }

    fun deleteEntryFromCard(cardId: CardId, cardEntryId: CardEntryId): Card {
        val card = cardRepository.getById(cardId)
        card.deleteEntry(cardEntryId)
        cardRepository.save(card)
        return card
    }
}
