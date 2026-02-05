@import ch.ranil.kortti.domain.card.Card
@import java.util.List

@param List<Card> cards

<table>
    <thead>
    <tr>
        <td>Type</td>
        <td>Content</td>
        <td>Created at</td>
        <td></td>
    </tr>
    </thead>
    <tbody id="cards-table">
    @for(var card : cards)
        @template.component.card-row(card = card)
    @endfor
    </tbody>
</table>
