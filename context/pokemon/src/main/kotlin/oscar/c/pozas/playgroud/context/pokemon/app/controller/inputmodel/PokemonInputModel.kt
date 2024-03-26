package oscar.c.pozas.playgroud.context.pokemon.app.controller.inputmodel

import oscar.c.pozas.playgroud.context.pokemon.domain.Pokemon

data class PokemonInputModel(
    val id: Int,
    val name: String
) {

    fun toDomain(): Pokemon = Pokemon(id = Pokemon.Id(id), name = Pokemon.Name(name))
}
