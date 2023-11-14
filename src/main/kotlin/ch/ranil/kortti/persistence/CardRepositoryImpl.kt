package ch.ranil.kortti.persistence

import ch.ranil.kortti.domain.Card
import ch.ranil.kortti.domain.CardId
import ch.ranil.kortti.domain.CardRepository
import org.slf4j.LoggerFactory

class CardRepositoryImpl : CardRepository {
    private val cards = mutableMapOf<CardId, Card>()

    override fun save(card: Card) {
        cards[card.id] = card
        LOGGER.info("Card saved: $card")
    }

    override fun find(id: CardId): Card? {
        return cards[id]
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(CardRepositoryImpl::class.java)
    }
}
