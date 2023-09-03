package com.example.KakaoTalk

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(properties = ["spring.config.location=classpath:application-test.yml"])
class KakaoTalkApplicationTests {

    @Test
    fun contextLoads() {
    }

}
