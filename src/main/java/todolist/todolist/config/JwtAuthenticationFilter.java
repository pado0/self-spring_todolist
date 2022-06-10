package todolist.todolist.config;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

// jwt가  유효한 토큰인지 인증하는 filter. JwtTokenProvider에서 발급한 토큰의 유효성 인증
public class JwtAuthenticationFilter extends GenericFilterBean {

    private JwtTokenProvider jwtTokenProvider; // jwt 토큰 생성 및 검증 모듈

    // jwt provider 주입. 이부분은 스프링에서 빈으로 처리하지 않아도 되는지.
    // 의존성주입 쓰지 않는 이유?
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // jwt 토큰의 유효성을 검증하는 필터를 필터 체인에 등록한다.
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }
}
