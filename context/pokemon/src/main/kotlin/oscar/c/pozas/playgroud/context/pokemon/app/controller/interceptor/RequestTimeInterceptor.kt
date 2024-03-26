package oscar.c.pozas.playgroud.context.pokemon.app.controller.interceptor

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor
import oscar.c.pozas.playground.kernel.logging.Logger

@Component
class RequestTimeInterceptor(private val logger: Logger) : WebRequestInterceptor {

    override fun preHandle(request: WebRequest) {
        request.setAttribute("startTime", System.currentTimeMillis(), RequestAttributes.SCOPE_REQUEST)
        logger.info(
            "New request ${request.getDescription(false)} " +
                "parameters: ${ObjectMapper().writeValueAsString(request.parameterMap)}"
        )
    }

    override fun postHandle(request: WebRequest, model: ModelMap?) {
        // Do nothing
    }

    override fun afterCompletion(request: WebRequest, ex: java.lang.Exception?) {
        val startTime = request.getAttribute("startTime", RequestAttributes.SCOPE_REQUEST) as Long
        logger.info(
            "Request time execution for " +
                "${request.getDescription(false)}: ${System.currentTimeMillis() - startTime} ms"
        )
    }
}
