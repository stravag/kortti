package ch.ranil.kortti.web

import ch.ranil.kortti.domain.card.CardService
import ch.ranil.kortti.templates.Templates
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(
    private val cardService: CardService,
    private val templates: Templates
) {

    @GetMapping("/")
    fun overviewPage(): String {
        val cards = cardService.getCards()
        return templates.pageOverview(cards).render()
    }

    @PostMapping("/card")
    fun createCard(@RequestBody data: CardFormData): String {
        val card = cardService.createCard(data)
        return templates.componentCardRow(card).render()
    }
}