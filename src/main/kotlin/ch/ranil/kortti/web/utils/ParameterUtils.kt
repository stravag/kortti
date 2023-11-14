package ch.ranil.kortti.web.utils

import ch.ranil.kortti.domain.CardId
import io.ktor.server.application.*

fun ApplicationCall.pathParam(name: String): CardId {
    return CardId.parse(requireNotNull(this.parameters[name]))
}