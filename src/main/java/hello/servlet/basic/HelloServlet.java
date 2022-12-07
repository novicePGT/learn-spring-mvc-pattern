package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service"); // soutm -> 클래스명 메소드명 + System.out.println
        System.out.println("request = " + request); // soutv 는 옆처럼 나옴
        System.out.println("response = " + response);

        String username = request.getParameter("username"); // url의 쿼리파라미터를 서블릿은 request.getParameter로 쉽게 읽어올 수 있음
        System.out.println("username = " + username);

        /* 응답 메세지에 값을 담아서 나감 & 텍스트 설정 */
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello " + username);
    }
}
