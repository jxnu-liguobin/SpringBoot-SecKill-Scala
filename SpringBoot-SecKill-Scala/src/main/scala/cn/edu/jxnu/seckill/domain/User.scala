package cn.edu.jxnu.seckill.domain

import scala.language.implicitConversions
import scala.beans.BeanProperty
import java.util.Date;

/**
 * 用户
 *
 * @author 梦境迷离.
 * @time 2018年5月19日
 * @version v1.0
 */
class User {

    //用户id
    @BeanProperty
    private[seckill] var id: Integer = _

    //用户名
    @BeanProperty
    private[seckill] var name: String = _

    override def toString(): String = {
        "User [id=" + id + ", name=" + name + "]"
    }

}