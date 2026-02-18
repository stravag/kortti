@import ch.ranil.kortti.domain.card.Card
@import java.time.format.DateTimeFormatter
@import java.time.format.FormatStyle

@param Card card

<tr id="card-${card.getId().getValue().toString()}">
    <td>${card.getType().name()}</td>
    <td>${card.getContent()}</td>
    <td>${card.getCreatedAt().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT))}</td>
    <td>
        <button hx-delete="/cards/${card.getId().getValue().toString()}"
                hx-target="body">
            Delete
        </button>
    </td>
</tr>
