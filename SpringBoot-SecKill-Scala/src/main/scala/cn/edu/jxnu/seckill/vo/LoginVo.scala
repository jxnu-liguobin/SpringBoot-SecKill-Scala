package cn.edu.jxnu.seckill.vo

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import cn.edu.jxnu.seckill.validator.IsMobile;
import scala.language.implicitConversions

/**
 * 登陆视图对象
 *
 * @author 梦境迷离.
 * @time 2018年5月20日
 * @version v1.0
 */
class LoginVo {

    /**
     * 手机号
     */
    @NotNull
    @IsMobile
    var mobile: String = _

    /**
     * 密码
     */
    @NotNull
    @Length(min = 32)
    var password: String = _

    override def toString() =
        "LoginVo [mobile=" + mobile + ", password=" + password + "]"

}