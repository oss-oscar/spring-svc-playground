package oscar.c.pozas.playground.infrastructure.datamodel

import com.fasterxml.jackson.annotation.JsonProperty
import oscar.c.pozas.playground.domain.model.Pokemon

data class PokemonResponseModel(
    @JsonProperty("id") val id: Int,
    @JsonProperty("name") val name: String
) {

    fun toDomain(): Pokemon = Pokemon(id = this.id, name = this.name)
}
