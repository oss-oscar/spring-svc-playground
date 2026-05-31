package oscar.c.pozas.playground.kernel.clock

import java.time.ZonedDateTime

fun interface Clock {

    fun now(): ZonedDateTime
}