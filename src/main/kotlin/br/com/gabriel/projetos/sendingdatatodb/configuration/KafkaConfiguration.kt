package br.com.gabriel.projetos.sendingdatatodb.configuration

import br.com.gabriel.projetos.sendingdatatodb.entities.Message
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
@EnableKafka
class KafkaConfiguration {

    @Value("\${spring.kafka.bootstrap-servers}")
    lateinit var bootstrapServers: String

    @Bean
    fun kafkaTemplate(producerFactory: ProducerFactory<String, Message>): KafkaTemplate<String, Message> {
        return KafkaTemplate(producerFactory)
    }

    @Bean
    fun consumerFactory(): ConsumerFactory<String, Message> {
        val properties = HashMap<String, Any>()
        properties["bootstrap.servers"] = bootstrapServers
        properties["key.deserializer"] = StringDeserializer::class.java
        properties["value.deserializer"] = JsonDeserializer::class.java
        properties["group.id"] = "group_id"
        return DefaultKafkaConsumerFactory(properties, StringDeserializer(), JsonDeserializer(Message::class.java))
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Message> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Message>()
        factory.consumerFactory = consumerFactory()
        return factory
    }
}
