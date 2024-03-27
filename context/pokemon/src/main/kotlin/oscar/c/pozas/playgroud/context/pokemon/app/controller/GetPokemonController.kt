package oscar.c.pozas.playgroud.context.pokemon.app.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import oscar.c.pozas.playgroud.context.pokemon.app.controller.viewmodel.PokemonViewModel
import oscar.c.pozas.playgroud.context.pokemon.app.controller.viewmodel.toViewModel
import oscar.c.pozas.playgroud.context.pokemon.app.usecase.GetPokemonByIdCommand
import oscar.c.pozas.playgroud.context.pokemon.app.usecase.GetPokemonByIdCommandHandler

@RestController
@RequestMapping("public/v1/pokemon")
class GetPokemonController(
    private val getPokemonById: GetPokemonByIdCommandHandler
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
    fun findById(@PathVariable("id") id: Int): PokemonViewModel = getPokemonById(GetPokemonByIdCommand(id)).toViewModel()
}