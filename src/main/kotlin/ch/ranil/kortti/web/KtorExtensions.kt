package ch.ranil.kortti.web

/*
inline fun <reified T : Id> ApplicationCall.idPathParam(name: String): T {
    val idString = requireNotNull(this.parameters[name])
    val id = UUID.fromString(idString)
    val constructor = requireNotNull(T::class.primaryConstructor)
    return constructor.call(id)
}

suspend fun ApplicationCall.respondTemplate(template: () -> JteModel) {
    respondText(template().render(), contentType = ContentType.Text.Html)
}

suspend fun ApplicationCall.respondRedirect303(url: String) {
    response.headers.append(HttpHeaders.Location, url)
    respond(HttpStatusCode.SeeOther)
}
*/