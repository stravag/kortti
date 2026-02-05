package ch.ranil.kortti.web

import ch.ranil.kortti.templates.Templates
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.concurrent.Executors
import kotlin.random.Random


@RestController
@RequestMapping("/stats")
class StatsController(
    private val templates: Templates
) {
    @GetMapping
    fun page(): String {
        return templates.pageStats().render()
    }

    @GetMapping("/load-stream")
    fun loadStream(): SseEmitter {
        val emitter = SseEmitter()
        val sseMvcExecutor = Executors.newSingleThreadExecutor()
        sseMvcExecutor.execute {
            try {
                var progress = 0
                do {
                    val event = SseEmitter.event()
                        .data(templates.componentStatsProgress(progress).render())
                    emitter.send(event)
                    progress += Random.nextInt(0, 10)
                    Thread.sleep(Random.nextLong(100, 500))
                } while (progress < 100)
                val event = SseEmitter.event()
                    .data("<img src=\"https://media0.giphy.com/media/v1.Y2lkPTc5MGI3NjExbmN4ZjB4MWVicGduNmttajdteDM0MWdpZDQzdW50cmNhdng2ODB5eCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/yYSSBtDgbbRzq/giphy.gif\">")
                emitter.send(event)
            } catch (ex: Exception) {
                emitter.completeWithError(ex)
            }
        }
        return emitter
    }
}