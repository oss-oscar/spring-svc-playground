package oscar.c.pozas.playground.app.controller.inputmodel

import oscar.c.pozas.playground.domain.model.Pokemon

data class PokemonInputModel(
    val id: Int,
    val name: String
) {

    fun toDomain(): Pokemon = Pokemon(id = id, name = name)
}
