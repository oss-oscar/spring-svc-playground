package oscar.c.pozas.playground.config.event

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import oscar.c.pozas.playground.kernel.event.DomainEventPublisher
import oscar.c.pozas.playground.kernel.event.KafkaDomainEventPublisher

@Configuration
class EventConfig {

    @Bean
    fun getEventDispacher(): DomainEventPublisher = KafkaDomainEventPublisher()
}