package ch.ranil.kortti.web.utils

import ch.ranil.kortti.domain.CardId
import io.ktor.server.application.*
import kotlinx.html.HTML
import kotlinx.html.HTMLTag
import kotlinx.html.TagConsumer
import kotlinx.html.emptyMap
import java.lang.reflect.Constructor

fun ApplicationCall.pathParam(name: String): CardId {
    return CardId.parse(requireNotNull(this.parameters[name]))
}

inline fun <reified T : HTMLTag> HTML.inCtxOf(block: T.() -> Unit) {
    val constructor = T::class.java.getConstructor(Map::class.java, TagConsumer::class.java)
    @Suppress("UNCHECKED_CAST") val tagConstructor = constructor as Constructor<T>

    val tag = tagConstructor.newInstance(emptyMap, consumer)
    with(tag, block)
}
