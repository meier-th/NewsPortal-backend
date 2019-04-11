package stGroup.newsportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    LoginSuccessHandler loginHandler;

    @Override
    protected void configure (HttpSecurity http) {
        try {
            http.authorizeRequests().antMatchers("/", "/home").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .successHandler(loginHandler)
                    .and()
                    .logout().deleteCookies("JSESSIONID").logoutSuccessUrl("/");
        } catch (Exception error) {
            System.out.println("Security initialization error has occured:");
            System.out.println(error.getMessage());
            error.printStackTrace();
            System.exit(1);
        }
    }

}
