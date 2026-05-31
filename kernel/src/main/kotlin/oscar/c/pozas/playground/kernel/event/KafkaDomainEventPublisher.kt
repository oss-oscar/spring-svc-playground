package oscar.c.pozas.playground.kernel.event

class KafkaDomainEventPublisher : DomainEventPublisher {

    override fun <E : DomainEvent> publish(event: E) {
//        TODO("Not yet implemented")
    }

    override fun <E : DomainEvent> publish(events: List<E>) {
//        TODO("Not yet implemented")
    }
}