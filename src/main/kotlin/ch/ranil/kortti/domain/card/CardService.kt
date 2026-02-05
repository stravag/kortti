package ch.ranil.kortti.domain.card

import ch.ranil.kortti.web.CardsController
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CardService(
    private val cardRepository: CardRepository,
) {
    fun createCard(data: CardsController.CardFormData): Card {
        val card = Card(
            id = CardId.random(),
            type = data.type,
            content = data.content.ifBlank { "MISSING" },
            createdAt = LocalDateTime.now()
        )
        cardRepository.save(card)
        return card
    }

    fun getCards(): List<Card> {
        return cardRepository.getAll()
    }

    fun deleteCard(id: CardId) {
        cardRepository.delete(id)
    }
}
