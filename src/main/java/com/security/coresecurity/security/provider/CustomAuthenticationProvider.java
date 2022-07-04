package com.security.coresecurity.security.provider;

import com.security.coresecurity.security.service.AccountContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    //검증을 위한 구현
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        //로그인 할때 입력한 ID, PW
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();


        AccountContext accountContext = (AccountContext) userDetailsService.loadUserByUsername(username);

        //패스워드 검증 (사용자가 입력한 패스워드, 데이터베이스에 저장된 패스워드)
        if (passwordEncoder.matches(password, accountContext.getAccount().getPassword())) {
            //패스워드 일치하지 않을때
            throw new BadCredentialsException("BadCredentialsException");
        }

        //검증성공, 토큰 생성 (account 정보, 패스워드(null), 권한)
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(accountContext.getAccount(), null, accountContext.getAuthorities());


        return authenticationToken;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
