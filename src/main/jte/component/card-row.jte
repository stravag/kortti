@import ch.ranil.kortti.domain.card.Card

@param Card card

<tr id="card-${card.getId().getValue().toString()}">
    <td>${card.getType().name()}</td>
    <td>${card.getContent()}</td>
    <td>${card.getCreatedAt().toString()}</td>
    <td>
        <button hx-delete="/cards/${card.getId().getValue().toString()}"
                hx-target="#card-${card.getId().getValue().toString()}"
                hx-swap="outerHTML">
            Delete
        </button>
    </td>
</tr>
