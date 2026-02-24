package ch.ranil.kortti.persistence

import ch.ranil.kortti.domain.card.Card
import ch.ranil.kortti.domain.card.CardId
import ch.ranil.kortti.domain.card.CardRepository
import ch.ranil.kortti.domain.card.CardType
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class CardRepositoryImpl : CardRepository {
    private val cards = mutableMapOf<CardId, Card>()

    init {
        listOf(
            Card(
                content = "To the moon 🚀",
                type = CardType.GENERIC,
                createdAt = LocalDateTime.of(1969, 7, 20, 20, 17)
            ),
            Card(
                content = "You're old mate! 🥳",
                type = CardType.BIRTHDAY,
                createdAt = LocalDateTime.of(1984, 11, 11, 17, 45)
            ),
            Card(
                content = "Forever Together ♾️❤️",
                type = CardType.WEDDING,
                createdAt = LocalDateTime.of(2014, 9, 26, 12, 0)
            ),
            Card(
                content = "Sad, so very sad",
                type = CardType.SYMPATHY,
                createdAt = LocalDateTime.of(2018, 4, 26, 23, 0)
            )
        ).forEach { save(it) }
    }

    override fun save(card: Card) {
        cards[card.id] = card
    }

    override fun getAll(): List<Card> {
        return cards.values.toList()
    }

    override fun delete(id: CardId) {
        cards.remove(id)
    }
}
