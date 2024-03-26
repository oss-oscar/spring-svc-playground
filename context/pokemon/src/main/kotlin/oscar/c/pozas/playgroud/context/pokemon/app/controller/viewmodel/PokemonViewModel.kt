package oscar.c.pozas.playgroud.context.pokemon.app.controller.viewmodel

import oscar.c.pozas.playgroud.context.pokemon.domain.Pokemon

data class PokemonViewModel(
    val id: Int,
    val name: String
)

fun Pokemon.toViewModel(): PokemonViewModel = PokemonViewModel(this.id.value, this.name.value)
