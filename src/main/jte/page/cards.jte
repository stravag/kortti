@import ch.ranil.kortti.domain.card.Card
@import ch.ranil.kortti.domain.card.CardType
@import java.util.List

@param List<Card> cards

@template.layouts.main(
title = "Kortti",
content = @`
    <section>
        HTMX showcasing hypermedia approach.
    </section>
    <section>
        <form hx-post="/cards"
              hx-target="body">
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
        <table>
            <thead>
            <tr>
                <td>Type</td>
                <td>Content</td>
                <td>Created at</td>
                <td></td>
            </tr>
            </thead>
            <tbody id="cards-table-body"
                   hx-get="/cards"
                   hx-trigger="load">
            @template.component.card-table-content(cards = cards)
            </tbody>
        </table>
    </section>
`)
