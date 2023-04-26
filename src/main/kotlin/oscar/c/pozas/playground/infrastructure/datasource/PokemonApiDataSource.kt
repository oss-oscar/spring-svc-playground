package oscar.c.pozas.playground.infrastructure.datasource

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import oscar.c.pozas.playground.common.annotation.DataSource
import oscar.c.pozas.playground.domain.model.Pokemon
import oscar.c.pozas.playground.infrastructure.datamodel.PokemonResponseModel
import java.util.*

@DataSource
class PokemonApiClientDataSource(
    private val pokemonApiClient: PokeApiV2Client
) {

    fun get(id: Int): Optional<Pokemon> = Optional.ofNullable(pokemonApiClient.getPokemonById(id.toString())?.toDomain())
}

@FeignClient(value = "PokeAPI", url = "\${pokemon.url}")
interface PokeApiV2Client {

    @RequestMapping(method = [RequestMethod.GET], value = ["/pokemon/{id}"])
    fun getPokemonById(@PathVariable("id") id: String): PokemonResponseModel?
}
