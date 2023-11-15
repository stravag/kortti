package ch.ranil.kortti.domain

import java.time.LocalDateTime
import kotlin.random.Random

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

        val charPool: List<Char> = ('a'..'z') + ('A'..'Z')
        val randomString = (1..10)
            .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
            .joinToString("")

        val entry = CardEntry(
            id = CardEntryId.random(),
            dateTime = LocalDateTime.now(),
            text = "Hello $randomString"
        )

        val extendedCard = card.copy(
            entries = card.entries + entry
        )

        cardRepository.save(extendedCard)

        return extendedCard
    }

    fun deleteEntryFromCard(cardId: CardId, cardEntryId: CardEntryId): Card {
        val card = requireNotNull(cardRepository.find(cardId))
        val extendedCard = card.copy(
            entries = card.entries.filterNot { it.id == cardEntryId }
        )

        cardRepository.save(extendedCard)

        return extendedCard
    }
}
