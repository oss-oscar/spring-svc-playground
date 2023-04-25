package oscar.c.pozas.playground.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import oscar.c.pozas.playground.app.controller.interceptor.RequestTimeInterceptor

@Configuration
class WebConfig : WebMvcConfigurer {

    @Autowired
    lateinit var requestTimeInterceptor: RequestTimeInterceptor

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addWebRequestInterceptor(requestTimeInterceptor).addPathPatterns("/public/v1/pokemon")
    }
}
