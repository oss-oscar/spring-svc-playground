package oscar.c.pozas.playgroud.context.pokemon.app.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import oscar.c.pozas.playgroud.context.pokemon.app.controller.inputmodel.PokemonInputModel
import oscar.c.pozas.playgroud.context.pokemon.app.controller.viewmodel.PokemonViewModel
import oscar.c.pozas.playgroud.context.pokemon.app.controller.viewmodel.toViewModel
import oscar.c.pozas.playgroud.context.pokemon.domain.Pokemon
import oscar.c.pozas.playgroud.context.pokemon.domain.service.PokemonCreator
import oscar.c.pozas.playgroud.context.pokemon.domain.service.PokemonFinder

@RestController
@RequestMapping("public/v1/pokemon")
@Tag(name = "Pokemon public API")
class PokemonController(
    private val pokemonFinder: PokemonFinder,
    private val pokemonCreator: PokemonCreator,
) {

    @GetMapping("/{id}")
    @Operation(summary = "Get a Pokemon by id")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "List of plans"
            ),
            ApiResponse(
                responseCode = "400",
                description = "The request was not correctly formed (missing required parameters, wrong types...)",
                content = [ Content(schema = Schema(hidden = true)) ]
            ),
            ApiResponse(
                responseCode = "500",
                description = "Generic error",
                content = [ Content(schema = Schema(hidden = true)) ]
            )
        ]
    )
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable("id") id: Int): PokemonViewModel =
        pokemonFinder(Pokemon.Id(id))!!.toViewModel()

    @PostMapping("/")
    @Operation(summary = "Create a new custom Pokemon")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody input: PokemonInputModel) = pokemonCreator(input.toDomain())
}
