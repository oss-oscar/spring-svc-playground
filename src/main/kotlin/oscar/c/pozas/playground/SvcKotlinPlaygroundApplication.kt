package oscar.c.pozas.playground

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SvcKotlinPlaygroundApplication

fun main(args: Array<String>) {
    runApplication<SvcKotlinPlaygroundApplication>(*args)
}
