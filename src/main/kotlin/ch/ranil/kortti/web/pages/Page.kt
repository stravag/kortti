package ch.ranil.kortti.web.pages

import ch.ranil.kortti.web.utils.htmx.hxBoost
import io.ktor.server.application.*
import io.ktor.server.html.*
import kotlinx.html.*
import java.time.LocalDateTime

suspend fun ApplicationCall.renderPage(
    title: String,
    content: BODY.() -> Unit = {}
) {
    respondHtml {
        head {
            title { +title }
            link {
                href = "/static/daisyui-4.2.2/full.min.css"
                rel = "stylesheet"
                type = "text/css"
            }
            favicon()
            script { src = "/static/htmx-1.9.8/htmx.min.js" }
            script { src = "/static/tailwindcss-3.3.5/tailwindcss.js" }
        }
        body {
            hxBoost()
            content()
            div {
                footer {
                    classes = setOf("footer", "footer-center", "p-4", "bg-base-300", "text-base-content")
                    aside {
                        p { +"Copyright © ${LocalDateTime.now().year}" }
                    }
                }
            }
        }
    }
}

fun HEAD.favicon() {
    link {
        href = "/static/apple-touch-icon.png"
        rel = "apple-touch-icon"
        sizes = "180x180"
    }
    link {
        href = "/static/favicon-32x32.png"
        rel = "icon"
        type = "image/png"
        sizes = "32x32"
    }
    link {
        href = "/static/favicon-16x16.png"
        rel = "icon"
        type = "image/png"
        sizes = "16x16"
    }
    link {
        href = "/static/site.webmanifest"
        rel = "manifest"
    }
}
