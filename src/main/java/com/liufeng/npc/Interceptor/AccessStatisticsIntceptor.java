package com.liufeng.npc.Interceptor;

import com.liufeng.npc.bean.AdminUser;
import com.liufeng.npc.bean.EditorMsg;
import com.liufeng.npc.bean.Msg;
import com.liufeng.npc.controller.AuthorityController;
import com.liufeng.npc.utils.Log;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccessStatisticsIntceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {


        //页面中的JS请求都拦截了 判断如果是admin的 全部拦截 其中login放行 如果有用户 则OK 否则为非法访问 跳转
        Log.logI( httpServletRequest.getRequestURI());



        return AuthorityController.isAuthorized(httpServletRequest,httpServletResponse);



    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
