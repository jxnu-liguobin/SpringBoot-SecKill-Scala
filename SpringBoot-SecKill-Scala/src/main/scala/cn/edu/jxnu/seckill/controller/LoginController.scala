package cn.edu.jxnu.seckill.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import cn.edu.jxnu.seckill.service.SeckillUserService
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletResponse
import cn.edu.jxnu.seckill.result.Result
import org.slf4j.LoggerFactory
import cn.edu.jxnu.seckill.vo.LoginVo
import javax.validation.Valid

/**
 * 登陆控制器
 *
 * @author 梦境迷离.
 * @time 2018年5月21日
 * @version v1.0
 */
@Controller
@RequestMapping(Array("/login"))
class LoginController @Autowired() (userService: SeckillUserService) {

    private final val log = LoggerFactory.getLogger(classOf[LoginController])
    
    @RequestMapping(Array("/to_login"))
    def toLogin() = "login"

    @RequestMapping(Array("/do_login"))
    @ResponseBody
    def doLogin(response: HttpServletResponse, @Valid loginVo: LoginVo): Result[String] = {
        
        log.info(LoginController.this.getClass().getSimpleName() + loginVo.toString())
        Result.success(userService.login(response, loginVo))
    }
}