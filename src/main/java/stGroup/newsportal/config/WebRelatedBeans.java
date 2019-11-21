package stGroup.newsportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class WebRelatedBeans {

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpStatusReturningLogoutSuccessHandler restLogoutHandler(){
        return new HttpStatusReturningLogoutSuccessHandler();
    }

}
