package oscar.c.pozas.playgroud.context.pokemon.infrastructure

import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Repository
import oscar.c.pozas.playgroud.context.pokemon.domain.Pokemon
import oscar.c.pozas.playgroud.context.pokemon.domain.repository.PokemonRepository
import oscar.c.pozas.playgroud.context.pokemon.infrastructure.datasource.PokemonApiClientDataSource
import oscar.c.pozas.playgroud.context.pokemon.infrastructure.datasource.PokemonStoreDataSource

@Repository
class PokemonDataRepository(
    private val pokemonApiClientDataSource: PokemonApiClientDataSource,
    private val pokemonStoreDataSource: PokemonStoreDataSource
) : PokemonRepository {

    @Cacheable(value = ["pokemon"])
    override fun findById(id: Pokemon.Id): Pokemon? =
        pokemonStoreDataSource.getById(id.value) ?: pokemonApiClientDataSource.get(id.value)
}
