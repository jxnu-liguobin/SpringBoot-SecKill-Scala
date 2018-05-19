package cn.edu.jxnu.seckill

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.mybatis.spring.annotation.MapperScan

/**
 * SpringBoot启动类配置
 */
@MapperScan(Array("cn.edu.jxnu.seckill.dao"))
@SpringBootApplication
class AppConfig