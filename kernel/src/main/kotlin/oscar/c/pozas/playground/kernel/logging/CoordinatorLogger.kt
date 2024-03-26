package oscar.c.pozas.playground.kernel.logging

class CoordinatorLogger(private val loggers: List<Logger>) : Logger {

    override fun debug(message: String) {
        loggers.forEach { it.debug(message) }
    }

    override fun error(message: String, throwable: Throwable) {
        loggers.forEach { it.error(message, throwable) }
    }

    override fun warn(message: String, throwable: Throwable?) {
        loggers.forEach { it.warn(message, throwable) }
    }

    override fun info(message: String) {
        loggers.forEach { it.info(message) }
    }
}
