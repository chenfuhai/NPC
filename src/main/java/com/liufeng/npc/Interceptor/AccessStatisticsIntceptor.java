package com.liufeng.npc.Interceptor;

import com.liufeng.npc.bean.AdminUser;
import com.liufeng.npc.bean.EditorMsg;
import com.liufeng.npc.bean.Msg;
import com.liufeng.npc.utils.Log;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccessStatisticsIntceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //权限处理先不管
//        String url = httpServletRequest.getRequestURI();
//        AdminUser user = (AdminUser) httpServletRequest.getSession().getAttribute("loginedUser");
//        if (!AuthorityController.isAuthorized(url,user)){
//            return false;
//        }

        String invalidAccessUrl = "/invalidAccess";
        //页面中的JS请求都拦截了 判断如果是admin的 全部拦截 其中login放行 如果有用户 则OK 否则为非法访问 跳转
        Log.logI( httpServletRequest.getRequestURI());

//
//        String url = httpServletRequest.getRequestURI().substring(httpServletRequest.getRequestURI().lastIndexOf("/"));
//        Log.logI(url);
//        AdminUser user = (AdminUser) httpServletRequest.getSession().getAttribute("loginedUser");
//        if (user == null){
//            if (url.startsWith("/login")){
//                return  true;
//            }else{
//                httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+invalidAccessUrl);
//                return false;
//            }
//        }else {
//            //用户不为空 正常访问 可以添加日志 或者在这里检查权限
//            return true;
//        }

        if(httpServletRequest.getRequestURI().contains("/admin")){
            String url = httpServletRequest.getRequestURI().substring(httpServletRequest.getRequestURI().lastIndexOf("/admin")+6);
            Log.logI(url);
            AdminUser user = (AdminUser) httpServletRequest.getSession().getAttribute("loginedUser");
            if (user == null){

                if (url.startsWith("/login")||url.equals("")||url.startsWith("/invalidAccess")||url.startsWith("/error")){
                    return  true;
                }else{
                    httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                    httpServletResponse.getWriter().print(EditorMsg.getError("非法访问，可能登陆已过期"));
                    httpServletResponse.sendRedirect(invalidAccessUrl);
                    return false;
                }
            }else {
                //用户不为空 正常访问 可以添加日志 或者在这里检查权限
                return true;
            }
        }else{
            return true;
        }


    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
