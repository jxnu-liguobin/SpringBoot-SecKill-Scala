package cn.edu.jxnu.seckill.config

import cn.edu.jxnu.seckill.access.UserContext
import cn.edu.jxnu.seckill.domain.SeckillUser
import cn.edu.jxnu.seckill.service.SeckillUserService
import com.sun.istack.internal.Nullable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Service
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.{HandlerMethodArgumentResolver, ModelAndViewContainer}

/**
 * 用户参数解析器
 *
 * @author 梦境迷离.
 * @time 2018年6月5日
 * @version v1.0
 */
@Service
class UserArgumentResolver @Autowired() (userService: SeckillUserService)
    extends HandlerMethodArgumentResolver {

    override def resolveArgument(methodParameter: MethodParameter, @Nullable modelAndViewContainer: ModelAndViewContainer,
        nativeWebRequest: NativeWebRequest, @Nullable webDataBinderFactory: WebDataBinderFactory): Object = {

        //线程私有的用户
        UserContext.getUser()
    }

    override def supportsParameter(methodParameter: MethodParameter): Boolean = {

        //判断类型
        methodParameter.getParameterType() == classOf[SeckillUser]
    }
}