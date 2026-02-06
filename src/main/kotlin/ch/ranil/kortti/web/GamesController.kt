package ch.ranil.kortti.web

import ch.ranil.kortti.templates.Templates
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.math.max


@RestController
@RequestMapping("/games")
class GamesController(
    private val templates: Templates
) {
    private var highscore = 0

    @GetMapping
    fun page(): String {
        return templates.pageGames(highscore).render()
    }

    @PostMapping("/highscore")
    fun saveScore(score: Int) {
        highscore = max(score, highscore)
    }
}