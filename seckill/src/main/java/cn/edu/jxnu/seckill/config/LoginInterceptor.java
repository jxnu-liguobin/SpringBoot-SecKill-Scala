package cn.edu.jxnu.seckill.config;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.jxnu.seckill.exception.GlobalException;
import cn.edu.jxnu.seckill.result.CodeMsg;
import cn.edu.jxnu.seckill.service.SeckillUserService;
import cn.edu.jxnu.seckill.util.CookieUtil;
import cn.edu.jxnu.seckill.validator.NeedLogin;

@Service
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private SeckillUserService seckillUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(o instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) o;
        Method method = handlerMethod.getMethod();
        NeedLogin needLogin = method.getAnnotation(NeedLogin.class);

        // 有 @NeedLogin 注解，需要认证
        if (needLogin != null) {
            String paramToken = request.getParameter(SeckillUserService.COOKIE_NAME_TOKEN);
            String cookieToken = CookieUtil.getCookieValue(request, SeckillUserService.COOKIE_NAME_TOKEN);

            if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
                return false;
            }
            String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
            if (seckillUserService.getByToken(response, token) == null) {
                throw new GlobalException(CodeMsg.SESSION_ERROR);
            }
            return true;
        }
        return true;
    }



    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
