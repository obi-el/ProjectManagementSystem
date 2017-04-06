package PMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.SecurityContextConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.persistence.EntityManagerFactory;
import javax.servlet.SessionTrackingMode;
import java.util.EnumSet;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

/**
 * Created by Jiang on 08/03/2017.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.headers().contentTypeOptions().disable();

        http.sessionManagement()
                .sessionFixation().migrateSession();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);


        http.sessionManagement().maximumSessions(2);



        http
                .authorizeRequests()

                .antMatchers("/coordinator").hasRole("ADMIN")

                .antMatchers("/", "/logout","/allProjects","/pickProject" ,"/signin", "/error", "/studentPage", "profPage" ,"/register", "/coordPage/sendreminder", "/studentPage/pickProject").permitAll()

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/home")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logoutPage")
                .invalidateHttpSession(true)
                .deleteCookies(SessionVariables.signedin)
                .deleteCookies(SessionVariables.user)
                .permitAll()
        .and().csrf().disable();


    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
