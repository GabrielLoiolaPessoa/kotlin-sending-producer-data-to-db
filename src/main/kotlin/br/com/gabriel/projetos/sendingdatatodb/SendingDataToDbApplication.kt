package br.com.gabriel.projetos.sendingdatatodb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
@EnableKafka
class SendingDataToDbApplication

fun main(args: Array<String>) {
    runApplication<SendingDataToDbApplication>(*args)
}
