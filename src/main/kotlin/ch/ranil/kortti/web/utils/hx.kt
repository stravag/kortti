package ch.ranil.kortti.web.utils

import kotlinx.html.HTMLTag

fun HTMLTag.hxPost(value: String) {
    attributes += "hx-post" to value
}

fun HTMLTag.hxBoost() {
    attributes += "hx-boost" to "true"
}
