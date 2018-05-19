package cn.edu.jxnu.seckill.util

import java.util.Properties
import cn.edu.jxnu.seckill.domain.Goods
import java.sql.Connection
import java.sql.DriverManager

/**
 * 数据库链接工具
 *
 * @author 梦境迷离.
 * @time 2018年5月19日
 * @version v1.0
 */
class DBUtil {
    /**
     * 这里用于拓展
     */

}
object DBUtil {

    private var props: Properties = _

    try {
        val in = classOf[DBUtil].getClassLoader().getResourceAsStream("application.properties")
        props = new Properties()
        props.load(in)
        in.close()
    } catch {
        case e: Exception =>
            e.printStackTrace()
    }

    def getConn(): Connection = {
        val url = props.getProperty("spring.datasource.url")
        val username = props.getProperty("spring.datasource.username")
        val password = props.getProperty("spring.datasource.password")
        val driver = props.getProperty("spring.datasource.driver-class-name")
        Class.forName(driver)
        return DriverManager.getConnection(url, username, password)
    }
}