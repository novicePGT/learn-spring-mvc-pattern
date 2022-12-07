package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV1 {

    // ControllerV1을 만드는 이유는 회원저장 컨트롤러, 회원 폼을 하는 컨트롤러, 회원 리스트 컨트롤러 등 여러가지가 나오는데 호출 할 때,
    // 다형성에 맞춰서 프론트 컨트롤러는 인터페이스에 의존하면서 편리하게 호출할 수 있다
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
