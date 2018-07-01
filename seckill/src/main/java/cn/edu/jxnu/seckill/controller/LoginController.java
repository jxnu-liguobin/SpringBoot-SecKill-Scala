package cn.edu.jxnu.seckill.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.jxnu.seckill.result.Result;
import cn.edu.jxnu.seckill.service.SeckillUserService;
import cn.edu.jxnu.seckill.vo.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/login")
@Slf4j
@Api(value = "登陆controller", tags = { "LoginAPI" })
public class LoginController {
	@Autowired
	private SeckillUserService seckillUserService;

	@ApiOperation("获取登录界面接口")
	@GetMapping("/to_login")
	public String toLogin() {
		return "login";
	}

	@ApiOperation("登录接口")
	@ApiImplicitParam(name = "loginVO", value = "登录实体", required = true, dataType = "LoginVO")
	@PostMapping("/do_login")
	@ResponseBody
	public Result<String> doLogin(HttpServletResponse response, @Valid LoginVO loginVO) {
		log.info("【用户登录】" + loginVO.toString());

		// 登录
		String token = seckillUserService.login(response, loginVO);
		return Result.success(token);
	}
}
