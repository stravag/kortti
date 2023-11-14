package ch.ranil.kortti.web.utils.htmx

import kotlinx.html.HTMLTag

fun HTMLTag.hxPost(value: String) {
    attributes += "hx-post" to value
}

fun HTMLTag.hxBoost() {
    attributes += "hx-boost" to "true"
}

fun HTMLTag.hxTarget(value: String) {
    attributes += "hx-target" to value
}

fun HTMLTag.hxSwap(value: HxSwap) {
    attributes += "hx-swap" to value.value
}

fun HTMLTag.hxSelect(value: String) {
    attributes += "hx-select" to value
}
