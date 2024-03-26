package oscar.c.pozas.playground.kernel.extension

inline fun <T, R> T?.ifNullOrElse(isNull: () -> R, notNull: (it: T) -> R): R = this?.let(notNull).orElse { isNull() }

inline fun <R> R?.orElse(block: () -> R): R = this ?: block()
fun <R> R?.orElse(value: R): R = this ?: value
