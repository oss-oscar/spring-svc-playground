package oscar.c.pozas.playgroud.context.pokemon.domain.repository

import oscar.c.pozas.playgroud.context.pokemon.domain.Pokemon

interface PokemonRepository {

    fun findById(id: Pokemon.Id): Pokemon?
}