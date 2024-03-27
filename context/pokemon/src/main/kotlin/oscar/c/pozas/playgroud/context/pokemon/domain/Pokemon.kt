package oscar.c.pozas.playgroud.context.pokemon.domain

data class Pokemon(
    val id: Id,
    val name: Name
) {

    data class Id(val value: Int)

    data class Name(val value: String)
}