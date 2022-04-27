package ltw.nhom6.blog.security.authentication;

import ltw.nhom6.blog.security.authentication.exception.handler.CustomAccessDeniedHandler;
import ltw.nhom6.blog.security.authentication.exception.handler.JwtAuthenticationEntryPoint;
import ltw.nhom6.blog.security.authentication.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter,
                                 JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                                 CustomAccessDeniedHandler customAccessDeniedHandler) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable().authorizeRequests()
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/user/active").permitAll()
                .antMatchers("/api/v1/user/change-password").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/api/v1/user/get-otp").permitAll()
                .antMatchers("/api/v1/user/register").permitAll()
                .antMatchers("/api/v1/user/forget-password/**").permitAll()
                .antMatchers("/api/v1/user/login").permitAll()
                .antMatchers("/api/v1/admin/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/api/v1/blog/create-blog").hasAnyAuthority("ROLE_USER")
                .antMatchers("/api/v1/blogs").hasAnyAuthority("ROLE_USER")
                .antMatchers("/api/v1/blog/edit-blog").hasAnyAuthority("ROLE_USER")
                .antMatchers("/api/v1/blog/add-comment").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/api/v1/blog/add-rate").hasAnyAuthority("ROLE_USER")
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(customAccessDeniedHandler)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
