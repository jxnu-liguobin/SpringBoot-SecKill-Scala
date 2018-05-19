package cn.edu.jxnu.seckill.util

import java.util.UUID

/**
 * UUID工具
 *
 * @author 梦境迷离.
 * @time 2018年5月19日
 * @version v1.0
 */
class UUIDUtil {

}

object UUIDUtil {

    def uuid() = UUID.randomUUID().toString().replace("-", "")

}