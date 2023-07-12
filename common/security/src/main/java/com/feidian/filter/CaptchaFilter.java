package com.feidian.filter;


import com.feidian.annotation.RequireCaptcha;
import com.feidian.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@Component
public class CaptchaFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 检查请求是否带有@RequireCaptcha注解
        if (hasRequireCaptchaAnnotation(request)) {
            //TODO
            // 从请求中获取用户输入的验证码
            String userInputCaptcha = request.getParameter("captcha");

            //TODO 获取用户名作为‘lable’
            // 从redisCache中获取生成的验证码
            String redisCaptchaKey =  request.getParameter("") + "verifyCode:";
            String generatedCaptcha = redisCache.getCacheObject(redisCaptchaKey);

            // 验证用户输入的验证码与生成的验证码是否匹配

            if (!generatedCaptcha.equals(userInputCaptcha)) {
                // 验证码不匹配，可以进行相应的处理，如跳转到错误页面或返回错误信息
                response.sendRedirect("/error");
                return;
            }

            // 验证码匹配，继续认证流程
            filterChain.doFilter(request, response);
        }

        // 对于不带@RequireCaptcha注解的请求，直接放行
        filterChain.doFilter(request, response);
    }

    private boolean hasRequireCaptchaAnnotation(HttpServletRequest request) {
        HandlerMethod handlerMethod = (HandlerMethod) request.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE);
        if (handlerMethod != null) {
            Method method = handlerMethod.getMethod();
            return method.isAnnotationPresent(RequireCaptcha.class);
        }
        return false;
    }
}



