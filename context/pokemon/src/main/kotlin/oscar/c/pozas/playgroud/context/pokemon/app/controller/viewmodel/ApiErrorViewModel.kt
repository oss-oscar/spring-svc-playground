package oscar.c.pozas.playgroud.context.pokemon.app.controller.viewmodel

import org.springframework.http.HttpStatus

data class ApiErrorViewModel(
    val message: String,
    val errorCodes: List<String> = listOf(),
    val statusError: HttpStatus
)
