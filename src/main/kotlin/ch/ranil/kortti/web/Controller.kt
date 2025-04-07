package ch.ranil.kortti.web

import ch.ranil.kortti.domain.card.CardService
import ch.ranil.kortti.templates.Templates
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(
    private val cardService: CardService,
    private val templates: Templates
) {

    @GetMapping("/")
    fun rootPage(): String {
        val cards = cardService.getCards()
        return templates.pageCardOverview(cards).render()
    }
}