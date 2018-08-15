package cn.edu.jxnu.seckill.domain

import java.util.Date

import scala.beans.BeanProperty
import scala.language.implicitConversions;

/**
 * 秒杀用户
 *
 * @author 梦境迷离.
 * @time 2018年5月19日
 * @version v1.0
 */
class SeckillUser {

    //秒杀用户实体  id，手机号码
    @BeanProperty
    private[seckill] var id: Long = _

    //用户昵称
    @BeanProperty
    private[seckill] var nickname: String = _

    //用户密码md5(md5(pass明文+固定salt)+随机salt)
    @BeanProperty
    private[seckill] var password: String = _

    //盐值
    @BeanProperty
    private[seckill] var salt: String = _

    //head 头像
    @BeanProperty
    private[seckill] var head: String = _

    //注册日期
    @BeanProperty
    private[seckill] var registerDate: Date = _

    //最近登陆日期
    @BeanProperty
    private[seckill] var lastLoginDate: Date = _

    //登陆次数
    @BeanProperty
    private[seckill] var loginCount: Integer = _

    override def toString(): String = {
        "SeckillUser [id=" + id + ", nickname=" + nickname + ", password=" + password +
            ", salt=" + salt + ", head=" + head + ", registerDate=" + registerDate + ", lastLoginDate=" + lastLoginDate +
            ", loginCount=" + loginCount + "]"
    }

}