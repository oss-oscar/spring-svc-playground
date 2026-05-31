package oscar.c.pozas.playground.config.mongo

import com.mongodb.kotlin.client.MongoClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MongoDbConfiguration {

    @Bean
    fun mongoAdminConfiguration(@Value("\${mongodb.connection}") connection: String): MongoClient =
        MongoClient.create(connectionString = connection)
}