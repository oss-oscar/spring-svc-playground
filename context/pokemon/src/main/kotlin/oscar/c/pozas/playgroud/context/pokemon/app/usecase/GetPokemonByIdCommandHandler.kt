package oscar.c.pozas.playgroud.context.pokemon.app.usecase

import oscar.c.pozas.playgroud.context.pokemon.domain.Pokemon
import oscar.c.pozas.playgroud.context.pokemon.domain.service.PokemonFinder

class GetPokemonByIdCommandHandler(private val pokemonFinder: PokemonFinder) {

    operator fun invoke(command: GetPokemonByIdCommand): Pokemon {
        val id = Pokemon.Id(command.id)
        return pokemonFinder(id)!!
    }
}

data class GetPokemonByIdCommand(val id: Int)