package oscar.c.pozas.playground.app.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import oscar.c.pozas.playground.app.controller.inputmodel.PokemonInputModel
import oscar.c.pozas.playground.app.controller.viewmodel.PokemonViewModel
import oscar.c.pozas.playground.app.controller.viewmodel.toViewModel
import oscar.c.pozas.playground.domain.usecases.CreatePokemonUseCase
import oscar.c.pozas.playground.domain.usecases.GetPokemonByIdUseCase

@RestController
@RequestMapping("public/v1/pokemon")
class PokemonController(
    private val getPokemonByIdUseCase: GetPokemonByIdUseCase,
    private val createPokemonUseCase: CreatePokemonUseCase
) {

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Int): PokemonViewModel =
        getPokemonByIdUseCase(id).orElseThrow().toViewModel()

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody input: PokemonInputModel) = createPokemonUseCase(input.toDomain())
}
