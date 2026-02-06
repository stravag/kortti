@template.layouts.main(
title = "Memes",
content = @`
    <section>
        Using SSE (Server Sent Events) to update the page in real-time.
    </section>
    <div hx-ext="sse" sse-connect="/memes/load-meme">
        <div sse-swap="message">
            Waiting for updates...
        </div>
    </div>
`)
