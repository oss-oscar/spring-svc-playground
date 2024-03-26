package oscar.c.pozas.playground.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import oscar.c.pozas.playground.kernel.logging.CoordinatorLogger
import oscar.c.pozas.playground.kernel.logging.Logger
import oscar.c.pozas.playground.spring.SpringStandardLogger

@Configuration
class LoggerConfig {

    @Bean
    fun getLogger(): Logger = CoordinatorLogger(listOf(SpringStandardLogger()))
}
