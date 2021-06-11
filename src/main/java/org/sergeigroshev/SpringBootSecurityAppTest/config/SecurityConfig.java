package org.sergeigroshev.SpringBootSecurityAppTest.config;

import org.sergeigroshev.SpringBootSecurityAppTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

/**
 * @author Sergei Y Groshev
 * @version 1.0
 */

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(securedEnabled = true) // this annotation we can secure methods
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    // MAIN HTTP CONFIGURE LOGIN LOGOUT FORM etc.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/authenticated/**").authenticated()
                .antMatchers("/admin_page/**").hasRole("ADMIN")
                .antMatchers("/read_profile/**").hasAuthority("READ_PROFILE")
                .and()
//                .httpBasic() // give us basic http style login popup form
                .formLogin() // give us beautiful SPRING login form
//                .defaultSuccessUrl("/authenticated")
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .csrf().disable(); // REMOVE CSRF TOKEN from FRAUD!!!;
    }

    // KEEP the USERS in DATABASE:

    //jdbc authentication

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }


    @Bean
    public JdbcUserDetailsManager users(DataSource dataSource) {
        // we can put default users in DB
//        UserDetails user1 = User.builder()
//                .username("user1")
//                .password("{bcrypt}$2y$12$d8Vbvyzz4c.Ftp9xJMW3XOu/PRMe/DF7664VLOIdgcJxTDyNvjKKe")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2y$12$d8Vbvyzz4c.Ftp9xJMW3XOu/PRMe/DF7664VLOIdgcJxTDyNvjKKe")
//                .roles("ADMIN", "USER")
//                .build();

        // this object put our usersDB to database
        JdbcUserDetailsManager usersDB = new JdbcUserDetailsManager(dataSource);

//        // check if default users (that we've just created already existed)
//        // if yes , delete them from database
//        if (usersDB.userExists(user1.getUsername())) {
//            usersDB.deleteUser(user1.getUsername());
//        }
//        if (usersDB.userExists(admin.getUsername())) {
//            usersDB.deleteUser(admin.getUsername());
//        }
//
//        // if no put them to database
//        usersDB.createUser(user1);
//        usersDB.createUser(admin);

        return usersDB;
    }


    // IF YOU WANT KEEP USERS inside the code:
    // we can use UserDetailsDService
    // Userdetails - INFORMATION about our users
    // we can create users of our app and keep them in RAM memory

    // In memory
//    @Bean
//    public UserDetailsService users() {
//        UserDetails user1 = User.builder()
//                .username("user1")
//                .password("{bcrypt}$2y$12$d8Vbvyzz4c.Ftp9xJMW3XOu/PRMe/DF7664VLOIdgcJxTDyNvjKKe")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2y$12$d8Vbvyzz4c.Ftp9xJMW3XOu/PRMe/DF7664VLOIdgcJxTDyNvjKKe")
//                .roles("ADMIN", "USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, admin);
//    }


}
