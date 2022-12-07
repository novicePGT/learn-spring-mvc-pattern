package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // [status-line]
        response.setStatus(HttpServletResponse.SC_OK); // 얘는 상태코드 200임
//        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Bad-Request 의 상태코드는 400이다

        // [response-header]
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 캐시를 완전 무효화하겠다 라는 뜻
        response.setHeader("Pragma", "no-cache"); // 과거에 있던 캐시마저 삭제
        response.setHeader("my-header", "hello"); // 내가 원하는 헤더를 만들 수 있음

        // [Header 편의 메서드]
//        content(response); // setHeader 의 컨텐트 타입과 인코딩을 설정할 수 있다. 그 외의 것도 가능
//        cookie(response); // 쿠키도 헤더로 설정할 수 있지만, Cookie 클래스를 지원한다. 고로 클래스를 활용하자 깔끔해진다.
//        redirect(response);

        // [message body]
        PrintWriter writer = response.getWriter();
        writer.println("확인되었습니다");
    }

    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)

    }

    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600; 이 쿠키의 상태와 600초동안 유효하다 라는 것을 나타냄
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html

//        response.setStatus(HttpServletResponse.SC_FOUND); //302
//        response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }
}
