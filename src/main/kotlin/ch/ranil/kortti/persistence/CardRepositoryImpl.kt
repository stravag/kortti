package ch.ranil.kortti.persistence

import ch.ranil.kortti.domain.card.Card
import ch.ranil.kortti.domain.card.CardId
import ch.ranil.kortti.domain.card.CardRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class CardRepositoryImpl : CardRepository {
    private val cards = mutableMapOf<CardId, Card>()

    override fun save(card: Card) {
        cards[card.id] = card
        LOGGER.info("Card saved: $card")
    }

    override fun getAll(): List<Card> {
        return cards.values.toList()
    }

    override fun getById(id: CardId): Card {
        return cards.getValue(id)
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(CardRepositoryImpl::class.java)
    }
}
