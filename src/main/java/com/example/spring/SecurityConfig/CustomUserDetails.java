package com.example.spring.SecurityConfig;

import com.example.spring.Entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails extends User implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        System.out.println("=========");
        getRoles().stream().forEach(System.out::println);

//        List<SimpleGrantedAuthority> grantedAuthorities;

//        return Arrays.asList(new SimpleGrantedAuthority("ROLE_"+getCurrentRoleId()));

        return getRoles().stream().map(
                e-> new SimpleGrantedAuthority("ROLE_"+e.getName()))
                .collect(Collectors.toSet());

    }


    @Override
    public String getUsername() {
        System.out.println("username os padmklflshjdfahjdfghjasdhj " + super.getFirstName());
        return super.getEmail();
    }

    @Override
   public String getPassword(){
        System.out.println("PASSword os padmklfl dhsjjjhdkskh " + super.getPassword());

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
