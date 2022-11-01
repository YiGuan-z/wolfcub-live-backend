package com.cqsd.net.interceptor;



import com.alibaba.fastjson.JSON;
import com.cqsd.utils.TokenManager;
import com.cqsd.vo.JsonResult;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Controller 的 方法执行之前执行
        String token = request.getHeader(TokenManager.TOKEN_NAME);
        // 是否有 token && token 是否正确
        if (StringUtils.hasText(token) && TokenManager.getInfo(token) != null) {
            // 如果有登录就放行
            return true;
        }

        // 响应结果给前端, 告诉前端当前没有登录
        JsonResult<Object> result = JsonResult.failed(401, "用户尚未登录, 请求登录后再访问");
        // 响应类型, 告诉前端我响应的是 json 数据
        response.setContentType("application/json;charset=utf-8");
        // 调用响应对象的响应流, 将数据写到前端
        response.getWriter().println(JSON.toJSONString(result));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Controller 的 方法执行之后执行
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 页面渲染前执行
    }
}
