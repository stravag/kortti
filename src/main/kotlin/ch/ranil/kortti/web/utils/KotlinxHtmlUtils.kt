package ch.ranil.kortti.web.utils

import ch.ranil.kortti.domain.Id
import ch.ranil.kortti.domain.card.CardEntryId
import ch.ranil.kortti.domain.card.CardId
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import kotlinx.html.HTML
import kotlinx.html.HTMLTag
import kotlinx.html.TagConsumer
import kotlinx.html.emptyMap
import java.lang.reflect.Constructor
import java.util.*
import kotlin.reflect.full.primaryConstructor

inline fun <reified T : Id> ApplicationCall.idPathParam(name: String): T {
    val idString = requireNotNull(this.parameters[name])
    val id = UUID.fromString(idString)
    val constructor = requireNotNull(T::class.primaryConstructor)
    return constructor.call(id)
}

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
