package oscar.c.pozas.playground.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import oscar.c.pozas.playground.common.logging.CoordinatorLogger
import oscar.c.pozas.playground.common.logging.Logger

@Configuration
class LoggerConfig {

    @Bean
    fun getLogger(): Logger = CoordinatorLogger()
}
