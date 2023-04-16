package oscar.c.pozas.playground.domain.usecases

import oscar.c.pozas.playground.common.annotation.UseCase
import oscar.c.pozas.playground.domain.model.Pokemon
import oscar.c.pozas.playground.domain.repository.PokemonRepository

@UseCase
class CreatePokemonUseCase(
    private val repository: PokemonRepository
) {

    operator fun invoke(pokemon: Pokemon) = repository.create(pokemon)
}
