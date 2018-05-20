package cn.edu.jxnu.seckill.exception

import scala.language.implicitConversions
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest
import cn.edu.jxnu.seckill.result.Result
import org.springframework.validation.BindException
import cn.edu.jxnu.seckill.result.CodeMsg
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * 全局处理
 *
 * 统一返回JSON
 *
 * @author 梦境迷离.
 * @time 2018年5月20日
 * @version v1.0
 */
@ControllerAdvice
@ResponseBody
class GlobalExceptionHandler {

    /**
     * 全局异常建言
     */
    @ExceptionHandler(value = Array(classOf[Exception]))
    def exceptionHandler(request: HttpServletRequest, exception: Exception): Result[String] = {

        exception.printStackTrace()
        if (exception.isInstanceOf[GlobalException]) {
            Result.error(exception.asInstanceOf[GlobalException].getCm())
        } else if (exception.isInstanceOf[BindException]) {
            Result.error(CodeMsg.BIND_ERROR.fillArgs(exception.asInstanceOf[BindException].getAllErrors().get(0).getDefaultMessage()))
        } else {
            Result.error(CodeMsg.SERVER_ERROR)
        }

    }

}