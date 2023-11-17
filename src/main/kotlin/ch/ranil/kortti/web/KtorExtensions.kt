package ch.ranil.kortti.web

import ch.ranil.kortti.domain.Id
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import java.util.*
import kotlin.reflect.full.primaryConstructor

inline fun <reified T : Id> ApplicationCall.idPathParam(name: String): T {
    val idString = requireNotNull(this.parameters[name])
    val id = UUID.fromString(idString)
    val constructor = requireNotNull(T::class.primaryConstructor)
    return constructor.call(id)
}

suspend fun ApplicationCall.respondRedirect303(url: String) {
    response.headers.append(HttpHeaders.Location, url)
    respond(HttpStatusCode.SeeOther)
}
