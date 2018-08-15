package cn.edu.jxnu.seckill.util

import java.io.{ByteArrayOutputStream, File, RandomAccessFile}
import java.net.{HttpURLConnection, URL}
import java.util.{ArrayList, Date}

import cn.edu.jxnu.seckill.domain.SeckillUser
import com.alibaba.fastjson.JSON

import scala.collection.immutable.{List => _}
import scala.language.implicitConversions

/**
 * 用户工具
 *
 * @author 梦境迷离.
 * @time 2018年5月19日
 * @version v1.0
 */
//class UserUtil {
//
//}

package object UserUtil {

    /**
     * 测试
     */
    def main(args: Array[String]): Unit = {
        createUser(5000);
    }

    private def createUser(count: Int) {

        val users = new ArrayList[SeckillUser](count)
        // 生成用户
        for (i <- 0 until count) {
            val user = new SeckillUser()
            user.setId(13000000000L + i)
            user.setLoginCount(1)
            user.setNickname("user" + i)
            user.setRegisterDate(new Date())
            user.setSalt("1a2b3c")
            user.setPassword(MD5Util.inputPassToDbPass("123456", user.getSalt()))
            users.add(user)
        }
        println("create user")
        //先生成token
        val urlString = "http://127.0.0.1:8080/login/do_login"
        val file = new File("D:/tokens.txt")
        if (file.exists()) {
            file.delete()
        }
        val raf = new RandomAccessFile(file, "rw")
        file.createNewFile()
        raf.seek(0)
        for (i <- 0 until users.size()) {
            val user = users.get(i)
            val url = new URL(urlString)
            val co = url.openConnection().asInstanceOf[HttpURLConnection]
            co.setRequestMethod("POST")
            co.setDoOutput(true)
            val out = co.getOutputStream()
            val params = "mobile=" + user.getId() + "&password=" + MD5Util.inputPassToFormPass("123456")
            out.write(params.getBytes())
            out.flush()
            val inputStream = co.getInputStream()
            val bout = new ByteArrayOutputStream()
            var buff = new Array[Byte](1024)
            //Scala赋值返回Unit,不能像Java一样直接放在括号中
            var len = inputStream.read(buff)
            while (len != -1) {
                bout.write(buff, 0, len)
                len = inputStream.read(buff)
            }
            inputStream.close()
            bout.close()
            val response = new String(bout.toByteArray())
            val jo = JSON.parseObject(response)
            val token = jo.getString("data")
            println("create token : " + user.getId())

            val row = user.getId() + "," + token
            raf.seek(raf.length())
            raf.write(row.getBytes())
            raf.write("\r\n".getBytes())
            println("write to file : " + user.getId())
        }
        raf.close()
        println("over")
    }

}