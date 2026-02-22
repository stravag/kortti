package ch.ranil.kortti.domain.card

import ch.ranil.kortti.web.CardsController
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@Service
class CardService(
    private val cardRepository: CardRepository,
) {
    init {
        cardRepository.save(
            Card(
                content = "To the moon 🚀",
                type = CardType.GENERIC,
                createdAt = LocalDateTime.of(1969, 7, 20, 20, 17)
            )
        )
        cardRepository.save(
            Card(
                content = "You're old mate! 🥳",
                type = CardType.BIRTHDAY,
                createdAt = LocalDateTime.of(1984, 11, 11, 17, 45)
            )
        )
        cardRepository.save(
            Card(
                content = "Forever Together ♾️❤️",
                type = CardType.WEDDING,
                createdAt = LocalDateTime.of(2014, 9, 26, 12, 0)
            )
        )
        cardRepository.save(
            Card(
                content = "Sad, so very sad",
                type = CardType.SYMPATHY,
                createdAt = LocalDateTime.of(2018, 4, 26, 23, 0)
            )
        )
    }

    fun createCard(data: CardsController.CardFormData): Card {
        sleepAtMost(300.milliseconds)
        val card = Card(
            type = data.type,
            content = data.content.ifBlank { "MISSING" },
        )
        cardRepository.save(card)
        return card
    }

    fun getCards(): List<Card> {
        sleepAtMost(1.seconds)
        return cardRepository.getAll()
    }

    fun deleteCard(id: CardId) {
        cardRepository.delete(id)
    }

    private fun sleepAtMost(duration: Duration = 1.seconds) {
        val sleepDuration = (Math.random() * duration.inWholeMilliseconds).toLong()
        Thread.sleep(sleepDuration)
    }
}
