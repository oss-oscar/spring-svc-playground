package oscar.c.pozas.playground.config.clock

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import oscar.c.pozas.playground.kernel.clock.Clock
import oscar.c.pozas.playground.kernel.clock.ClockUTC

@Configuration
class ClockConfiguration {

    @Bean
    fun getClock(): Clock = ClockUTC()
}