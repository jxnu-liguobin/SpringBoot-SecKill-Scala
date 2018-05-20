package cn.edu.jxnu.seckill.config

import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import cn.edu.jxnu.seckill.service.SeckillUserService
import org.springframework.core.MethodParameter
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.bind.support.WebDataBinderFactory
import com.sun.istack.internal.Nullable
import cn.edu.jxnu.seckill.access.UserContext
import cn.edu.jxnu.seckill.domain.SeckillUser

@Service
class UserArgumentResolver @Autowired() (val userService: SeckillUserService)
    extends HandlerMethodArgumentResolver {

    override def resolveArgument(methodParameter: MethodParameter, @Nullable modelAndViewContainer: ModelAndViewContainer,
        nativeWebRequest: NativeWebRequest, @Nullable webDataBinderFactory: WebDataBinderFactory): Object = {
        UserContext.getUser()
    }

    override def supportsParameter(methodParameter: MethodParameter): Boolean = {
        val clazz = methodParameter.getParameterType()
        return clazz == classOf[SeckillUser]
    }
}