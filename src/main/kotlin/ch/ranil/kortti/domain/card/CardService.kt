package ch.ranil.kortti.domain.card

import ch.ranil.kortti.web.CardFormData
import org.springframework.stereotype.Service

@Service
class CardService(
    private val cardRepository: CardRepository,
) {
    fun createCard(data: CardFormData): Card {
        val card = Card(
            id = CardId.random(),
            name = data.name.ifBlank { "MISSING" },
        )
        cardRepository.save(card)
        return card
    }

    fun getCards(): List<Card> {
        return cardRepository.getAll()
    }

    fun getCard(cardId: CardId): Card {
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
