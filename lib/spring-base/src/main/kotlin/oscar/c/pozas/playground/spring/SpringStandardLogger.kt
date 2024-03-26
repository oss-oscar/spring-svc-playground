package oscar.c.pozas.playground.spring

import oscar.c.pozas.playground.kernel.logging.Logger
import org.slf4j.LoggerFactory

class SpringStandardLogger : Logger {

    private val logger = LoggerFactory.getLogger(SpringStandardLogger::class.java)

    override fun debug(message: String) {
        logger.debug(message)
    }

    override fun error(message: String, throwable: Throwable) {
        logger.error(message, throwable)
    }

    override fun warn(message: String, throwable: Throwable?) {
        logger.warn(message, throwable)
    }

    override fun info(message: String) {
        logger.info(message)
    }
}
