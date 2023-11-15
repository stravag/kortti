package ch.ranil.kortti.web.utils

import ch.ranil.kortti.domain.CardEntryId
import ch.ranil.kortti.domain.CardId
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlinx.html.HTML
import kotlinx.html.HTMLTag
import kotlinx.html.TagConsumer
import kotlinx.html.emptyMap
import java.lang.reflect.Constructor

fun ApplicationCall.cardIdPathParam(name: String): CardId {
    return CardId.parse(requireNotNull(this.parameters[name]))
}

fun ApplicationCall.cardEntryIdPathParam(name: String): CardEntryId {
    return CardEntryId.parse(requireNotNull(this.parameters[name]))
}

suspend fun ApplicationCall.respondRedirect303(url: String) {
    response.headers.append(HttpHeaders.Location, url)
    respond(HttpStatusCode.SeeOther)
}

inline fun <reified T : HTMLTag> HTML.inCtxOf(block: T.() -> Unit) {
    val constructor = T::class.java.getConstructor(Map::class.java, TagConsumer::class.java)
    @Suppress("UNCHECKED_CAST") val tagConstructor = constructor as Constructor<T>

    val tag = tagConstructor.newInstance(emptyMap, consumer)
    with(tag, block)
}
