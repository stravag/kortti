@import ch.ranil.kortti.domain.card.CardType

@template.layouts.main(
title = "Kortti",
content = @`
    <section>
        HTMX showcasing hypermedia approach.
    </section>
    <section>
        <form hx-post="/cards"
              hx-target="#cards-table"
              hx-swap="beforeend"
              hx-on::after-request="this.reset()">
            <fieldset class="grid">
                <select name="type" aria-label="Type" required>
                    @for(var type : CardType.getEntries())
                        <option value="${type.name()}">${type.toString()}</option>
                    @endfor
                </select>
                <input name="content" type="text" placeholder="Content" aria-label="Content" required/>
                <button type="submit">Create
                    <span class="spinner">⏳</span>
                </button>
            </fieldset>
        </form>
    </section>
    <section>
        <h2>Cards</h2>
        <div hx-get="/cards" hx-trigger="load">
            <span class="spinner">⏳</span>
        </div>
    </section>
`)
