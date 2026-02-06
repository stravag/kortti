@template.layouts.main(
title = "Memes",
content = @`
    <button hx-get="/memes/load-meme" hx-target="#stream-output">Load asynchronously</button>
    <section id="stream-output">
    </section>
`)
