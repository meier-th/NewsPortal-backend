package stGroup.newsportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

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
            System.out.println("Security initialization error has occurred:");
            System.out.println(error.getMessage());
            error.printStackTrace();
            System.exit(1);
        }
    }

    @Autowired
    public void initialize(AuthenticationManagerBuilder builder, DataSource dataSource) {
        try {
            builder.jdbcAuthentication().dataSource(dataSource);
        } catch (Exception error) {
            System.out.println("AuthenticationManager initialization has thrown an exception:");
            System.out.println(error.getMessage());
            error.printStackTrace();
            System.exit(1);
        }
    }

}
