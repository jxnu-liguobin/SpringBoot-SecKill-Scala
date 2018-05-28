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
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiImplicitParam
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

/**
 * 登陆控制器
 *
 * @author 梦境迷离.
 * @time 2018年5月21日
 * @version v1.0
 */
@Controller
@RequestMapping(Array("/login"))
@Api(value = "登陆controller", tags = { Array("登陆接口") })
class LoginController @Autowired() (userService: SeckillUserService) {

    private final val log = LoggerFactory.getLogger(classOf[LoginController])

    @ApiOperation(value = "访问登陆页面", notes = { "访问登陆页面" })
    @GetMapping(Array("/to_login"))
    def toLogin() = "login"

    /**
     * {
     * "mobile": "15312345678",
     * "password": "d3b1294a61a07da9b49b6e22b2cbd7f9"
     * }
     *
     * RequestBody 必须
     */
    @ApiOperation(value = "登陆", notes = { "登陆" })
    @ApiImplicitParam(name     = "loginVo", value = "LoginVo", required = true, dataType = "LoginVo")
    @PostMapping(Array("/do_login"))
    @ResponseBody
    def doLogin(response: HttpServletResponse, @RequestBody @Valid loginVo: LoginVo): Result[String] = {

        log.info(LoginController.this.getClass().getSimpleName() + loginVo.toString())
        Result.success(userService.login(response, loginVo))
    }
}