package todolist.todolist.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
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

        // 요청 헤더에서 파싱한 토큰 읽어오기
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        // 토큰유효성, 만료일자 확인
        if(token != null && jwtTokenProvider.validateToken(token)){

            // jwt 토큰으로 인증정보 조회.
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            // 조회한 토큰으로 뭘 하는거지?
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response); // 다음 체인으로 이동
    }
}
