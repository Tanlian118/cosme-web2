package com.cosme.common.interceptor;

import com.cosme.util.SessionUtils;
import com.cosme.web.common.ResultDTO;
import com.cosme.web.common.StateCode;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author Tanlian
 * @create 2018-08-25 18:40
 **/
public class PermissionInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        response.setContentType("text/json;charset=utf-8");
        String userId = SessionUtils.getUserId(response, request);
        if (!StringUtils.hasText(userId)) {
            ResultDTO resultDTO = ResultDTO.fail(StateCode.USER_OFFLINE, "用户未登录");
            PrintWriter writer = response.getWriter();
            writer.print(resultDTO);
            writer.close();
            return false;
        }

        return true;
    }


}
