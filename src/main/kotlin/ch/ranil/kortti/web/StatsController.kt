package ch.ranil.kortti.web

import ch.ranil.kortti.templates.Templates
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/stats")
class StatsController(
    private val templates: Templates
) {
    @GetMapping
    fun page(): String {
        return templates.pageStats().render()
    }
}