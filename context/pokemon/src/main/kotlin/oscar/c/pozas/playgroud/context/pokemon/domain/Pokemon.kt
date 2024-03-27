package oscar.c.pozas.playgroud.context.pokemon.domain

data class Pokemon(
    val id: Id,
    val name: Name,
) {

    data class Id(val value: Int) {

        init {
            if (value <= 0) throw IllegalStateException("Pokemon Id can't be negative") // TODO: Use domain exception
        }
    }

    data class Name(val value: String)
}