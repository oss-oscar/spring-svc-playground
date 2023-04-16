package oscar.c.pozas.playground.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackages = ["oscar.c.pozas.playground"])
class CloudConfig
