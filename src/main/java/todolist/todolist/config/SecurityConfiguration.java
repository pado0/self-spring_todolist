package todolist.todolist.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

// 서버에 전반적인 보안들 설정
// 예제로 나온 WebSecurityConfi-adapter가 deprecated. 어떡하지
@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration {

    private final JwtTokenProvider jwtTokenProvider;

    // 필터체인을 적용한다. 우선 테스트만
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() // restapi 로 개발중이므로 기본설정 사용x
                .csrf().disable()// rest api이므로 csrf 보안 필요없음
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// jwt는 session 사용 안하므로 생성 안함
                .and()
                .authorizeRequests()// 다음 리퀘스트에 대한 사용 권한 체크
                .antMatchers(HttpMethod.GET, "/").permitAll()// get은 누구나 접근 가능
                .antMatchers(HttpMethod.POST, "/").permitAll()//hasRole("MEMBER")// post는 멤버권한만 가능
                .and() // jwt token 필터를 id/password 인증 필터 전에 넣음
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    // ignore할 페이지 링크 추가한다
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");

    }
}
