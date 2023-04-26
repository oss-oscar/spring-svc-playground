package oscar.c.pozas.playground.domain.repository

import oscar.c.pozas.playground.domain.model.Pokemon
import java.util.Optional

interface PokemonRepository {

    fun create(pokemon: Pokemon): Pokemon

    fun findById(id: Int): Optional<Pokemon>
}
