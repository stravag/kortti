@template.layouts.main(
title = "Memes",
content = @`
    <div hx-ext="sse" sse-connect="/memes/load-meme">
        <div sse-swap="message">
            Waiting for updates...
        </div>
    </div>
`)
