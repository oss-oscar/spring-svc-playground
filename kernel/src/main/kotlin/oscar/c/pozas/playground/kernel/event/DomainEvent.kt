package oscar.c.pozas.playground.kernel.event

import java.time.ZonedDateTime

abstract class DomainEvent(
    val aggregateId: String,
    val payload: Map<String, String> = emptyMap(),
    val metadata: Metadata = Metadata()
) {

    abstract fun getType(): String

    data class Metadata(
        val occurredOn: ZonedDateTime = ZonedDateTime.now(),
        val version: Long = 1
    )
}