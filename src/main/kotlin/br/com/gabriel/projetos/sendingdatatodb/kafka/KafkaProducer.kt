package br.com.gabriel.projetos.sendingdatatodb.kafka

import br.com.gabriel.projetos.sendingdatatodb.entities.Message
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducer {

    @Autowired
    lateinit var kafkaTemplate: KafkaTemplate<String, Message>

    fun sendMessage(topic: String, message: Message) {
        kafkaTemplate.send(topic, message)
    }
}