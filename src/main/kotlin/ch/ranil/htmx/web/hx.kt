package ch.ranil.htmx.web

import kotlinx.html.HTMLTag

fun HTMLTag.hxPost(value: String) {
    attributes += "hx-post" to value
}

fun HTMLTag.hxTarget(value: String) {
    attributes += "hx-target" to value
}
