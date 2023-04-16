package oscar.c.pozas.playground.app.controller

import feign.FeignException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import oscar.c.pozas.playground.app.controller.viewmodel.ApiErrorViewModel
import oscar.c.pozas.playground.common.logging.Logger

@ControllerAdvice
class GlobalExceptionHandler(private val logger: Logger) {

    @ExceptionHandler(Exception::class)
    fun handleGenericException(exception: Exception): ResponseEntity<ApiErrorViewModel> {
        val message = "Unexpected error"
        val status = HttpStatus.INTERNAL_SERVER_ERROR
        logger.error(message, exception)
        return ResponseEntity(ApiErrorViewModel(message = message, statusError = status), status)
    }

    @ExceptionHandler(FeignException::class)
    fun handleFeignException(exception: FeignException): ResponseEntity<ApiErrorViewModel> {
        val message = exception.contentUTF8()
        val status = HttpStatus.valueOf(exception.status())
        logger.error(message, exception)
        return ResponseEntity(ApiErrorViewModel(message = message, statusError = status), status)
    }
}
