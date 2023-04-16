package oscar.c.pozas.playground.common.logging

interface Logger {

    fun debug(message: String)

    fun error(message: String, throwable: Throwable)

    fun warn(message: String, throwable: Throwable?)

    fun info(message: String)
}
