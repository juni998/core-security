package com.security.coresecurity.security.service;

import com.security.coresecurity.domain.Account;
import com.security.coresecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = userRepository.findByUsername(username);

        if (account == null) {
            //인증실패 예외
            throw new UsernameNotFoundException("UsernameNotFoundException");
        }


        //권한 정보 생성
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(account.getRole()));

        AccountContext accountContext = new AccountContext(account, roles);

        return accountContext;
    }

}
