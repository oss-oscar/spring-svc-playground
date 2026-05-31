package oscar.c.pozas.playground.context.user.domain

import java.time.ZonedDateTime
import java.util.UUID
import oscar.c.pozas.playground.kernel.event.Aggregate
import oscar.c.pozas.playground.kernel.event.DomainEvent

data class User private constructor(
    val id: Id,
    val name: Name,
    val createdOn: ZonedDateTime
): Aggregate() {

    @JvmInline value class Id(val value: UUID) {

        constructor(id: String) : this(UUID.fromString(id))

        override fun toString(): String = value.toString()
    }

    @JvmInline value class Name(val value: String) {

        override fun toString(): String = value
    }

    companion object {

        fun create(id: Id, name: Name, now: ZonedDateTime): User =
            User(id, name, now)
    }

    sealed class Error : RuntimeException() {
        object PlayerNotFound : Error()
        object PlayerAlreadyExists : Error()
    }
}
