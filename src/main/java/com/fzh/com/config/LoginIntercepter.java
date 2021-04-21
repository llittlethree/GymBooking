package com.fzh.com.config;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**登录拦截器
 * @author 张小三
 * @create 2021-04-21 0:59
 * @verson 1.0.0
 */

public class LoginIntercepter implements HandlerInterceptor{
    /**
    *不过滤的目标
    */
    private final String[] NO_FILTER_PAGES = {"/","/login"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        System.out.println("====== 开始 ======");

        //判断是否在不过滤的目标中
        String getUrl = request.getRequestURI();
        for (String url : NO_FILTER_PAGES ){
            if(url.equals(getUrl)){
                return  true;
            }
        }


        HttpSession session = request.getSession();

        if(session.getAttribute("adminLoginInfo") != null){
            System.out.println("存在session");
            return true;
        }else{
            System.out.println("不存在session");
            invalidate(request,response);

            return false;
        }

    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("====== 结束 ======");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("====== 清理 ======");
    }

    private void invalidate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        String ajax = request.getHeader("X-Requested-With");
        if (StringUtils.equals(ajax,"XMLHttpRequest")) {
            //设置登陆超时header
            response.addHeader("sessionout","true");
        } else {
            response.sendRedirect("/gymBooking/");
        }
    }
}
