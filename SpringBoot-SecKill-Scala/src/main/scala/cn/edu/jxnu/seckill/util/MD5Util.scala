package cn.edu.jxnu.seckill.util

import org.apache.commons.codec.digest.DigestUtils

/**
 * MD5工具
 *
 * @author 梦境迷离.
 * @time 2018年5月19日
 * @version v1.0
 */
//class MD5Util {
//
//}
package object MD5Util {

    /**
     * 测试
     */
    def main(args: Array[String]): Unit = {
        println("模拟客户端:" + inputPassToFormPass("123456")) // d3b1294a61a07da9b49b6e22b2cbd7f9
        println("模拟服务端：" + formPassToDBPass(inputPassToFormPass("123456"), "1a2b3c4d"))
        println("模拟password客户端到服务器端整个流程:" + inputPassToDbPass("123456", "1a2b3c4d")) // b7797cce01b4b131b433b6acf4add449
    }

    def md5(src: String) = { DigestUtils.md5Hex(src) }

    private final val salt: String = "1a2b3c4d"

    /**
     * 模拟客户端md5加密
     */
    def inputPassToFormPass(inputPass: String): String = {
        val str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4)
        println(str)
        md5(str)
    }

    /**
     * 服务端md5加密
     */
    def formPassToDBPass(formPass: String, salt: String): String = {
        val str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4)
        md5(str)
    }

    /**
     * 模拟password客户端到服务器端整个流程
     */
    def inputPassToDbPass(inputPass: String, saltDB: String): String = {
        val formPass = inputPassToFormPass(inputPass)
        val dbPass = formPassToDBPass(formPass, saltDB)
        dbPass
    }

}