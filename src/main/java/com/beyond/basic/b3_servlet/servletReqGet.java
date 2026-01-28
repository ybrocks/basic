package com.beyond.basic.b3_servlet;

import com.beyond.basic.b1_basic.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.PrintWriter;

//서블릿(servlet)은 사용자의 http요청을 쉽게 처리하고 사용자에게 http응답을 쉽게 조립해주는 기술
//서블릿에서는 url매핑을 메서드 단위가 아닌 클래스 단위로 지정
@WebServlet("/servlet/get")
public class servletReqGet extends HttpServlet {
    private final ObjectMapper objectMapper;

    @Autowired
    public servletReqGet(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        사용자 요청 : http://localhostt:8080/servlet/get?id=1
//        사용자 응답 : json 객체 형식

        String id = req.getParameter("id");
        System.out.println(id);

        Member member = new Member("hong1", "hong1@naver.com");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String data = objectMapper.writeValueAsString(member);
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(data);
        printWriter.flush();
    }
}
