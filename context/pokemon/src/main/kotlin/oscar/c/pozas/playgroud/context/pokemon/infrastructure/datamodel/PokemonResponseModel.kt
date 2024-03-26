package oscar.c.pozas.playgroud.context.pokemon.infrastructure.datamodel

import com.fasterxml.jackson.annotation.JsonProperty
import oscar.c.pozas.playgroud.context.pokemon.domain.Pokemon

data class PokemonResponseModel(
    @JsonProperty("id") val id: Int,
    @JsonProperty("name") val name: String
) {

    fun toDomain(): Pokemon = Pokemon(id = Pokemon.Id(this.id), name = Pokemon.Name(this.name))
}
