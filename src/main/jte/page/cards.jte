@import ch.ranil.kortti.domain.card.CardType

@template.layouts.main(
title = "Cards",
subTitle = "HTMX showcasing hypermedia approach",
content = @`
    <section>
        <!-- form to create a new card -->
        <form hx-post="/cards"
              hx-target="#cards-table-body"
              hx-swap="innerHTML">
            <fieldset class="grid">
                <select name="type" aria-label="Type" required>
                    @for(var type : CardType.getEntries())
                        <option value="${type.name()}">${type.name()}</option>
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
            <tr>
                <td colspan="4"><span class="spinner">⏳</span></td>
            </tr>
            </tbody>
        </table>
    </section>
`)
