@template.layouts.main(
title = "Stats",
content = @`
    <button hx-get="/stats/load-stream" hx-target="#stream-output">Load Stats</button>
    <section id="stream-output">
    </section>
`)
