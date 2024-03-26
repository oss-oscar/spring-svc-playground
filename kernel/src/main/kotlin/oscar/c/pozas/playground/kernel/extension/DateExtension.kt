package oscar.c.pozas.playground.kernel.extension

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun LocalDateTime.format(dateFormat: String): String = DateTimeFormatter.ofPattern(dateFormat).format(this)

fun LocalDateTime.formatToHumanDate(): String = format("yyyy-MM-dd")

fun LocalDateTime.formatToHumanTime(): String = format("HH:mm:ss")

fun Instant.toLocalDateTime(): LocalDateTime = LocalDateTime.ofInstant(this, ZoneId.of("UTC"))
