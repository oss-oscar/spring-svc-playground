package oscar.c.pozas.playground.domain.repository

import oscar.c.pozas.playground.domain.model.Pokemon
import java.util.*

interface PokemonRepository {

    fun create(pokemon: Pokemon)

    fun findById(id: Int): Optional<Pokemon>
}
