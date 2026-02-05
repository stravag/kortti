package ch.ranil.kortti.domain.card

import ch.ranil.kortti.domain.Id
import ch.ranil.kortti.domain.IdFactory
import java.util.*

data class CardId(override val value: UUID) : Id {
    companion object : IdFactory<CardId>(CardId::class)
}
