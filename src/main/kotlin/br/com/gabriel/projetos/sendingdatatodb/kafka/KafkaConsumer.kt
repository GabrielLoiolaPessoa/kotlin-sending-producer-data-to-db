package br.com.gabriel.projetos.sendingdatatodb.kafka

import br.com.gabriel.projetos.sendingdatatodb.entities.Message
import br.com.gabriel.projetos.sendingdatatodb.repositories.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaConsumer {
    @Autowired
    lateinit var messageRepository : MessageRepository

    @KafkaListener(topics = ["topic1"])
    fun consumeMessage(message: Message){
        messageRepository.save(message)
    }

}