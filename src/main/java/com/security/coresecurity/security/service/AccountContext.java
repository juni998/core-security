package com.security.coresecurity.security.service;


import com.security.coresecurity.domain.Account;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class AccountContext extends User {
    private Account account;

    public Account getAccount() {
        return account;
    }

    public AccountContext(Account account, List<GrantedAuthority> roles) {
        super(account.getUsername(), account.getPassword(), roles);
        this.account = account;

    }

}
