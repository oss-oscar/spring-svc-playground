package oscar.c.pozas.playgroud.context.pokemon.domain.service

import oscar.c.pozas.playgroud.context.pokemon.domain.Pokemon
import oscar.c.pozas.playgroud.context.pokemon.domain.repository.PokemonRepository

class PokemonCreator(private val repository: PokemonRepository) {

    operator fun invoke(pokemon: Pokemon) {
        ensurePokemonNotExists()

        repository.create(pokemon)
    }

    private fun ensurePokemonNotExists() {
        // TODO: Implement me pls
    }
}