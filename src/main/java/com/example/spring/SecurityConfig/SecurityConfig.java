package com.example.spring.SecurityConfig;


import com.example.spring.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean(name = "BCryptPasswordEncoder")
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }


    //for redirectiion to different pages
    @Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
            AuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();


        return super.authenticationManagerBean();
    }


    @Autowired
    private CustomUserDetailsService userDetailsService;

//    @Bean
//    public AuthenticationProvider authProvide(){
//
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence rawPassword) {
//
//                return rawPassword.toString();
//            }
//
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                if(rawPassword.equals(encodedPassword))
//                return true;
//                else return false;
//            }
//        });
//
//        return provider;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        System.out.println("configure");
//        http.httpBasic().disable();

        System.out.println("again");
        http.csrf().disable();

//        http.authorizeRequests().antMatchers("/*.png","/resources/**", "/static/**","/images/**","/css/**").permitAll();
//        http.formLogin()
//                .loginPage("/loginUser.html")
//                .loginProcessingUrl("/pe")
//                .defaultSuccessUrl("/homepage.html",true)
//                .failureUrl("/login.html?error=true");





        http.authorizeRequests()
                .antMatchers("**/dashboard/**").permitAll()
                .anyRequest().authenticated().
                and()

                .formLogin().loginPage("/loginSignup").loginProcessingUrl("/perform_login").defaultSuccessUrl("/dashboard").
                permitAll().
                and().
                logout().
                logoutUrl("/logout").clearAuthentication(true).
                logoutSuccessUrl("/loginSignup").
                 permitAll();

//                .loginProcessingUrl("/rishabh")
//                .defaultSuccessUrl("/dashboard")
//                .failureUrl("/loginUser")
//                .and().logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
//                System.out.println("raw " + rawPassword);
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {

//                System.out.println("i am heree");
//                System.out.println((rawPassword).toString());
//                System.out.println("encoded "+encodedPassword);
//                rawPassword= BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt());
//                System.out.println("raw "+rawPassword);
                System.out.println(BCrypt.checkpw(rawPassword.toString(), encodedPassword));
                return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
            }
        });
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        System.out.println("inside config web security");
        web.ignoring()
                .antMatchers(HttpMethod.GET, "/*.png","/images/**","**/profileImages/**","/assets/**", "/resources/**", "**/static/**", "/images/**", "/css/**");


    }


}
