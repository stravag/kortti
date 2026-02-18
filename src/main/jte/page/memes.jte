@template.layouts.main(
title = "Memes",
subTitle = "Using SSE (Server Sent Events) to update the page in real-time",
content = @`
    <div id="meme-container">
        <div hx-ext="sse"
             sse-connect="/memes/load-meme">

            <div sse-swap="message">
                Waiting for updates...
            </div>

            <div sse-swap="completed"
                 hx-target="#meme-container">
            </div>
        </div>
    </div>
`)
