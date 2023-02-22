package com.jimecc.diaryserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimecc.diaryserver.entity.CommonResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Jim
 * @Description
 * @createTime 2023年02月13日
 */
@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        CommonResponse cr = CommonResponse.error("尚未登录，请登录！!-");
        cr.setStatus(401);
        writer.write(new ObjectMapper().writeValueAsString(cr));
        writer.flush();
        writer.close();
    }
}