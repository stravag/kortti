@import ch.ranil.kortti.web.MemesController

@template.layouts.main(
title = "Memes",
subTitle = "Using SSE (Server Sent Events) to update the page in real-time",
content = @`
    <button hx-post="/memes/meme-broadcast" hx-swap="none">Send Meme</button>
    <div hx-ext="sse"
         sse-connect="/memes/meme-listener"
         sse-swap="${MemesController.MEME_MSG_TYPE}">
        Waiting for memes...
    </div>
`)
