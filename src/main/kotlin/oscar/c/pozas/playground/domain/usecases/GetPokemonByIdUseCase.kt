package oscar.c.pozas.playground.domain.usecases

import oscar.c.pozas.playground.common.annotation.UseCase
import oscar.c.pozas.playground.domain.model.Pokemon
import oscar.c.pozas.playground.domain.repository.PokemonRepository
import java.util.Optional

@UseCase
class GetPokemonByIdUseCase(
    private val repository: PokemonRepository
) {

    operator fun invoke(id: Int): Optional<Pokemon> {
        if (id < 0) { throw IllegalStateException("Poke id cannot be negative") }
        return repository.findById(id)
    }
}
