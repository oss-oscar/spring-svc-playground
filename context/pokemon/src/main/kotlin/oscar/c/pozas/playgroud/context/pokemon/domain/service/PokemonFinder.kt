package oscar.c.pozas.playgroud.context.pokemon.domain.service

import oscar.c.pozas.playgroud.context.pokemon.domain.Pokemon
import oscar.c.pozas.playgroud.context.pokemon.domain.repository.PokemonRepository

class PokemonFinder(private val repository: PokemonRepository) {

    fun findBy(id: Pokemon.Id): Pokemon? = repository.findById(id)
}