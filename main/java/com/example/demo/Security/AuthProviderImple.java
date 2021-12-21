package com.example.demo.Security;

import com.example.demo.Models.admin;
import com.example.demo.repom.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class AuthProviderImple implements AuthenticationProvider {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();

        admin admin = adminRepository.findByLogin(login);
        if (admin == null){
            throw new UsernameNotFoundException("UserN=NotFound");
        }
        String password = authentication.getCredentials().toString();
        if (!password.equals(admin.getPass())) {
            throw new BadCredentialsException("Bad Credetional");
        }
        List <GrantedAuthority> authority = new ArrayList();
        return new UsernamePasswordAuthenticationToken(admin,null,authority);
    }




    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
