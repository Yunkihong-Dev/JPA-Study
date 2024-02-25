package com.app.jwtsecurity.config.jwt;

import com.app.jwtsecurity.config.auth.PrincipalDetails;
import com.app.jwtsecurity.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

// 스프링 시큐리티에서 UsernamePasswordAuthenticationFilter가 있음
// /login 요청해서
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;





//    /login 요청을 하면 로그인 시도를 위해서 실행되는 함
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        log.info("JwtAuthenticationFilter : 로그인 시도중");

        // 1. username, password 받아서
        try{
            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(request.getInputStream(),User.class);
            log.info(user.toString());

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUserName(),user.getUserPassword());

            // PrinciplDetailsService가 호출, loadUserByUserName 함수가 실행된 후 정상이면 authentication이 리턴됨.
//            DB에 있는 username과 password가 일치한다.

            Authentication authentication=
                    authenticationManager.authenticate(authenticationToken);

            // authentication 개체가 session 영역에 저장됨 => 로그인이 되었다는 뜻.
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            log.info(principalDetails.getUser().getUserName());



            // authentication 객체가 session 영역에 저장을 해야하고 그 방법이 return을 해주는 것.
            // 리턴의 이유는 권한 관리르 security 가 대신해 주기에 편하려고 하는거임.
            // 굳이 JWT 토큰을 사용하면서 세션을 만들 이유가 없음. 근데 단지 권한 처리 때문에 session을 넣어줌.
            return authentication;
        }catch (IOException e){
            e.printStackTrace();

        }

        // 2. 정상인지 로그인  시도를 해보는 거임. authenticationManager로 로그인 시도를 하면
        // PrinciplDetailsService가 호출, loadUserByUserName 함수가 실행됨

        // 3. PrinciplDetails를 세션에 담고

        // 4. JWT 토큰을 만들어서 응답해주면 됨.
        return null;
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("로그인 성공된거임");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

//        RSA 방식은 아니고 Hash 암호 방식
        String jwtToken = JWT.create()
                .withSubject("cos 토큰")
                .withExpiresAt(new Date(System.currentTimeMillis()+(1000*60*10)))
                .withClaim("id",principalDetails.getUser().getId())
                .withClaim("username", principalDetails.getUser().getUserName() )
                .sign(Algorithm.HMAC512("cos"));

        response.addHeader("Authorization","Bearer "+jwtToken);
    }
}
