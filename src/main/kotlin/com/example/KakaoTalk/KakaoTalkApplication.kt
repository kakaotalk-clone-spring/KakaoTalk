package com.example.KakaoTalk

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.EnableMongoAuditing

@SpringBootApplication
@EnableMongoAuditing
class KakaoTalkApplication

fun main(args: Array<String>) {
	runApplication<KakaoTalkApplication>(*args)
}
