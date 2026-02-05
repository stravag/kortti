@template.layouts.main(
title = "Memes",
content = @`
    <button hx-get="/memes/load-meme" hx-target="#stream-output">Load</button>
    <section id="stream-output">
    </section>
`)
