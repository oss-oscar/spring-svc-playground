package oscar.c.pozas.playgroud.context.pokemon.domain.service

import oscar.c.pozas.playgroud.context.pokemon.domain.Pokemon
import oscar.c.pozas.playgroud.context.pokemon.domain.repository.PokemonRepository

class PokemonCreator(
    private val repository: PokemonRepository,
    private val pokemonFinder: PokemonFinder,
) {

    operator fun invoke(pokemon: Pokemon) {
        ensureNotExists(pokemon.id)

        repository.save(pokemon)
    }

    private fun ensureNotExists(id: Pokemon.Id) {
        if (pokemonFinder.findBy(id) != null) throw IllegalStateException() // TODO: use a domain exception
    }
}