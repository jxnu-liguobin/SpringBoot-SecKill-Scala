package cn.edu.jxnu.seckill

import org.springframework.boot.SpringApplication

object SpringBootSecKillScalaApplication extends App {
    //classof相当于getClass/.class
    SpringApplication.run(classOf[AppConfig])
}