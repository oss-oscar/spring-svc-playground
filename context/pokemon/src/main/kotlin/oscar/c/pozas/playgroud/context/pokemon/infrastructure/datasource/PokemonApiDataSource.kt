package oscar.c.pozas.playgroud.context.pokemon.infrastructure.datasource

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import oscar.c.pozas.playgroud.context.pokemon.domain.Pokemon
import oscar.c.pozas.playgroud.context.pokemon.infrastructure.datamodel.PokemonResponseModel
import oscar.c.pozas.playground.spring.annotation.DataSource

@DataSource
class PokemonApiClientDataSource(
    private val pokemonApiClient: PokeApiV2Client
) {

    fun get(id: Int): Pokemon? = pokemonApiClient.getPokemonById(id.toString())?.toDomain()
}

@FeignClient(value = "PokeAPI", url = "\${pokemon.url}")
interface PokeApiV2Client {

    @RequestMapping(method = [RequestMethod.GET], value = ["/pokemon/{id}"])
    fun getPokemonById(@PathVariable("id") id: String): PokemonResponseModel?
}
