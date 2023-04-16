package oscar.c.pozas.playground.app.controller.viewmodel

import oscar.c.pozas.playground.domain.model.Pokemon

data class PokemonViewModel(
    val id: Int,
    val name: String
)

fun Pokemon.toViewModel(): PokemonViewModel = PokemonViewModel(this.id, this.name)
