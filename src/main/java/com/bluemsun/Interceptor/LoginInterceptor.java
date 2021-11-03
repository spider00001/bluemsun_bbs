package com.bluemsun.Interceptor;

import com.alibaba.fastjson.JSON;
import com.bluemsun.utils.JWTUtil;
import com.bluemsun.utils.JedisUtil;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//登录拦截器
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger LOG = Logger.getLogger(LoginInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String responseData = null;
        // 获取请求头中的token验证字符串
        String headerToken =null;
        // 检测当前页面，设置当前页不是登录、注册页面时就对其进行拦截
        // 具体方法就是检测URL中有没有 login 或 register 字符串
        if (!request.getRequestURI().contains("login")&&!request.getRequestURI().contains("register")) {
            headerToken = request.getHeader("token");
            if (headerToken != null) {
                try {
                    // 对token更新与验证
                    headerToken = JWTUtil.updateToken(headerToken);
                    LOG.debug("token验证通过,并且续期了");
                } catch (Exception e) {
                    LOG.debug("token过期了!");
                    // 这里的ResponseData类自定义的返回信息类
                    responseData = "The token has expired!";
                }
            } else {
                // 如果没有token，返回错误信息
                responseData = "There is no token!";
            }
        }
        // 如果有错误信息
        if (responseData != null) {
            response.getWriter().write(JSON.toJSONString(responseData));
            return false;
        } else {
            // 将token加入返回的header中
            response.setHeader("token", headerToken);
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
