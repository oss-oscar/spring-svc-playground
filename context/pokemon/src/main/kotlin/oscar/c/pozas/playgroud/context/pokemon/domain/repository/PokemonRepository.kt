package oscar.c.pozas.playgroud.context.pokemon.domain.repository

import oscar.c.pozas.playgroud.context.pokemon.domain.Pokemon

interface PokemonRepository {

    fun create(pokemon: Pokemon): Pokemon

    fun findById(id: Pokemon.Id): Pokemon?
}
