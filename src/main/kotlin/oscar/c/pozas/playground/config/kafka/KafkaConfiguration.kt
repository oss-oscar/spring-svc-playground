package oscar.c.pozas.playground.config.kafka

import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaAdmin

@Configuration
@EnableKafka
class KafkaConfiguration {

//    @Bean
//    fun kafkaAdminConfiguration(): KafkaAdmin {
//        val configs = hashMapOf<String, Any>().apply {
//            put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
//        }
//        return KafkaAdmin(configs)
//    }

//    @Bean
//    fun playerTopic(): NewTopic =
//        TopicBuilder.name("player")
//            .partitions(2)
//            .replicas(1)
//            .compact()
//            .build()
}