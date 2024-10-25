package org.example.gather_back_end.util.jwt.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



//AuthenticationFilter
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public LoginFilter(AuthenticationManager authenticationManager){
        this.authenticationManager=authenticationManager;
    }

    //가로채는 역할 username, password
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = obtainUsername(request);

        System.out.println(username);

        // 원래 null 아닌 role
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,  null);

        // 매니저한테 전달
        return authenticationManager.authenticate(authToken);
    }

    //검증 성공 jwt token 발행

    //검증 실패
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,HttpServletResponse response,AuthenticationException failed){
        response.setStatus(401);
    }
}
