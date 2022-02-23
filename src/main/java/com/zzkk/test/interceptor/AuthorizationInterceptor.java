package com.zzkk.test.interceptor;

import com.zzkk.test.annotation.Token;
import com.zzkk.test.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.invoke.MethodHandle;

/**
 * 权限的校验拦截器
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    public static final String USER_KEY="USER_ID";
    public static final String USER_INFO="USER_INFO";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Token annotation;
        if (handler instanceof HandlerMethod){
            annotation=((HandlerMethod)handler).getMethodAnnotation(Token.class);
        }else {
            return true;
        }
        //没有声明需要权限，或者声明不验证权限
        if(annotation==null||annotation.validate()==false){
            return true;
        }
        //从header中获取token
        String token=request.getHeader("token");
        if (token==null){
            return false;
        }

       //token校验通过，将用户信息放在request中，供需要用user信息的接口里从token取数据
        request.setAttribute(USER_KEY,"123456");
        User user=new User();
        user.setId(1000L);
        user.setUserName("zhangkai");
        user.setPhoneNumber("1111");
        user.setToken(token);
        request.setAttribute(USER_INFO,user);
        return true;
    }
}
