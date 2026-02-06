@import ch.ranil.kortti.domain.card.Card
@import java.util.List

@param List<Card> cards

@for(var card : cards)
    @template.component.card-row(card = card)
@endfor
