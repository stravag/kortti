package ch.ranil.kortti.domain.card

import ch.ranil.kortti.persistence.CardRepositoryImpl
import ch.ranil.kortti.web.CardsController
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@Service
class CardService(
    private val cardRepository: CardRepository,
) {

    fun createCard(data: CardsController.CardFormData): Card {
        sleepAtMost(300.milliseconds)
        val card = Card(
            type = data.type,
            content = data.content.ifBlank { "MISSING" },
        )
        cardRepository.save(card)
        LOGGER.info("Card saved: $card")
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

    companion object {
        private val LOGGER = LoggerFactory.getLogger(CardRepositoryImpl::class.java)
    }
}
