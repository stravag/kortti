@template.layouts.main(
title = "Memes",
subTitle = "Using SSE (Server Sent Events) to update the page in real-time",
content = @`
    <button hx-post="/memes/meme-broadcast" hx-swap="none">Send Meme</button>
    <div hx-ext="sse"
         sse-connect="/memes/meme-listener"
         sse-swap="meme">
        Waiting for memes...
    </div>
`)
