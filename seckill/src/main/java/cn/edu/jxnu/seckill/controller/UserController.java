package cn.edu.jxnu.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.jxnu.seckill.domain.SeckillUser;
import cn.edu.jxnu.seckill.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/user")
@Api(value = "用户controller", tags = { "用户接口" })
public class UserController {

	@ApiOperation(value = "获取用户信息接口", notes = "获取用户信息")
	@RequestMapping("/info")
	@ResponseBody
	public Result<SeckillUser> doLogin(SeckillUser seckillUser) {
		return Result.success(seckillUser);
	}
}
