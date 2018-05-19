package cn.edu.jxnu.seckill.domain
import scala.language.implicitConversions
import scala.beans.BeanProperty
import java.util.Date;

/**
 * 秒杀用户
 *
 * @author 梦境迷离.
 * @time 2018年5月19日
 * @version v1.0
 */
class SeckillUser {

    //秒杀用户实体  id
    @BeanProperty
    private[domain] var id: Long = _

    //用户昵称
    @BeanProperty
    private[domain] var nickname: String = _

    //用户密码
    @BeanProperty
    private[domain] var password: String = _

    //盐值
    @BeanProperty
    private[domain] var salt: String = _

    //head
    @BeanProperty
    private[domain] var head: String = _

    //注册日期
    @BeanProperty
    private[domain] var registerDate: Date = _

    //最近登陆日期
    @BeanProperty
    private[domain] var lastLoginDate: Date = _

    //登陆次数
    @BeanProperty
    private[domain] var loginCount: Integer = _

}