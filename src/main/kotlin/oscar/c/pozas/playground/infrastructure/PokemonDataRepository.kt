package oscar.c.pozas.playground.infrastructure

import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Repository
import oscar.c.pozas.playground.domain.model.Pokemon
import oscar.c.pozas.playground.domain.repository.PokemonRepository
import oscar.c.pozas.playground.infrastructure.datasource.PokemonApiClientDataSource
import oscar.c.pozas.playground.infrastructure.datasource.PokemonStoreDataSource
import java.util.*

@Repository
class PokemonDataRepository(
    private val pokemonApiClientDataSource: PokemonApiClientDataSource,
    private val pokemonStoreDataSource: PokemonStoreDataSource
) : PokemonRepository {

    override fun create(pokemon: Pokemon) = pokemonStoreDataSource.save(pokemon)

    @Cacheable(value = ["pokemon"])
    override fun findById(id: Int): Optional<Pokemon> =
        pokemonStoreDataSource.getById(id).or { pokemonApiClientDataSource.get(id) }
}
