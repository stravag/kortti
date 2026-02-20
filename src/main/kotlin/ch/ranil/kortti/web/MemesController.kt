package ch.ranil.kortti.web

import ch.ranil.kortti.templates.Templates
import org.springframework.context.event.ContextClosedEvent
import org.springframework.context.event.EventListener
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.io.IOException
import java.util.concurrent.CopyOnWriteArrayList


@RestController
@RequestMapping("/memes", produces = ["text/html"])
class MemesController(
    private val templates: Templates
) {
    private val emitters: MutableList<SseEmitter> = CopyOnWriteArrayList()

    @GetMapping
    fun page(): String {
        return templates.pageMemes().render()
    }

    @GetMapping("/meme-listener", produces = ["text/event-stream"])
    fun loadMeme(): SseEmitter {
        val emitter = SseEmitter(Long.MAX_VALUE)
        this.emitters.add(emitter)

        emitter.onCompletion { this.emitters.remove(emitter) }
        emitter.onTimeout { this.emitters.remove(emitter) }
        emitter.onError { _ -> this.emitters.remove(emitter) }

        return emitter
    }

    @PostMapping("/meme-broadcast")
    fun broadcastMeme() {
        val event = SseEmitter.event()
            .name(MEME_MSG_TYPE)
            .data("<img src=\"${memes.random()}\">")

        emitters.forEach { emitter ->
            try {
                emitter.send(event)
            } catch (_: IOException) {
                emitters.remove(emitter)
            }
        }
    }

    @EventListener
    fun onApplicationEvent(event: ContextClosedEvent) {
        emitters.forEach {
            try {
                // 'complete()' might be ignored by a busy container.
                // 'completeWithError' is more likely to break the connection.
                it.completeWithError(Exception("Shutdown"))
            } catch (e: Exception) {
            }
        }
        emitters.clear()
    }

    private val memes = listOf(
        "https://media0.giphy.com/media/v1.Y2lkPTc5MGI3NjExbmN4ZjB4MWVicGduNmttajdteDM0MWdpZDQzdW50cmNhdng2ODB5eCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/yYSSBtDgbbRzq/giphy.gif",
        "https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExMDVyY3k1YmphNGpyMTl3Mm1keDQwaTZrbnl0Y2R0aWM3ZDlpbXQyYyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/9xnNG7EN2h822ithtT/giphy.gif",
        "https://media1.giphy.com/media/v1.Y2lkPTc5MGI3NjExNnp2eXVkNXA5OHJjbGlvNzUyZ2MyaGRvcmpscng4bWd0cWNiZGd3bCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/Cz6TlrRVVyv9S/giphy.gif",
        "https://media4.giphy.com/media/v1.Y2lkPTc5MGI3NjExZHQ2eWNxa29ieW8zcGxjZXhiZWpjZmY3OHFwZGZ5Z2N0bzZteWp4YiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/FspLvJQlQACXu/giphy.gif",
        "https://media4.giphy.com/media/v1.Y2lkPTc5MGI3NjExZ2ZvMGQxNHhxM3Z5NHZvdTRyMHY1MnBkYXU5ZDIwam1sN2phamJ3MiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/xTiTnwgQ8Wjs1sUB4k/giphy.gif",
    )

    companion object {
        const val MEME_MSG_TYPE = "meme"
    }
}