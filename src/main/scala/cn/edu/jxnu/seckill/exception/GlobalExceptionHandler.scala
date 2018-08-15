package cn.edu.jxnu.seckill.exception

import cn.edu.jxnu.seckill.result.{CodeMsg, Result}
import javax.servlet.http.HttpServletRequest
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.{ControllerAdvice, ExceptionHandler, ResponseBody}

import scala.language.implicitConversions

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

        //打印堆栈，不然可能不知道具体的异常信息
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