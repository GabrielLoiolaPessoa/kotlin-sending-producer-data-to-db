package br.com.gabriel.projetos.sendingdatatodb.controllers

import br.com.gabriel.projetos.sendingdatatodb.entities.Message
import br.com.gabriel.projetos.sendingdatatodb.kafka.KafkaProducer
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/messages")
class MessageController(private val kafkaProducer: KafkaProducer) {
    @PostMapping
    fun sendMessage(@RequestBody message: Message){
        kafkaProducer.sendMessage("topic1", message)
    }
}