package oscar.c.pozas.playground.kernel.clock

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

class ClockUTC : Clock {

    override fun now(): ZonedDateTime = ZonedDateTime.now(ZoneId.of("UTC").normalized()).truncatedTo(ChronoUnit.MILLIS)
}