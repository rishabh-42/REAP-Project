package com.example.spring.SecurityConfig;

import com.example.spring.Entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class CustomUserDetails extends User implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        System.out.println("Hiiii");
        System.out.println(getCurrentRoleId());
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_"+getCurrentRoleId()));
    }

    @Override
    public String getUsername() {
        System.out.println("username os padmklfl " + super.getFirstName());
        return super.getFirstName();
    }

    @Override
   public String getPassword(){
        System.out.println("PASSword os padmklfl " + super.getPassword());

        return super.getPassword();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public CustomUserDetails(final User user){
        super(user);
    }




}
