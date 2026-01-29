package ch.ranil.kortti.domain

import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

interface Id {
    val value: UUID
}

open class IdFactory<T : Id>(klass: KClass<T>) {
    private val constructor = requireNotNull(klass.primaryConstructor)

    fun random(): T = constructor.call(UUID.randomUUID())
}
