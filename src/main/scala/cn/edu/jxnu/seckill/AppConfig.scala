package cn.edu.jxnu.seckill

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * SpringBoot启动类配置
 */
@MapperScan(Array("cn.edu.jxnu.seckill.dao"))
@SpringBootApplication
class AppConfig