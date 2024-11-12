package nirmalya.aatithya.restmodule.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import nirmalya.aatithya.restmodule.security.config.JwtAuthenticationEntryPoint;
import nirmalya.aatithya.restmodule.security.config.JwtRequestFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Configure AuthenticationManager with user details service and password encoder
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // Disable CSRF protection for stateless API
        httpSecurity.csrf().disable()
            // Specify allowed HTTP methods for specific paths
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/authenticate").permitAll()
            .antMatchers(HttpMethod.POST, "/authenticate").permitAll()
            .antMatchers(HttpMethod.GET, "/document/**").permitAll()
            .antMatchers(HttpMethod.POST, "/document/**").permitAll()
            .antMatchers(HttpMethod.GET, "/user/**").permitAll()
            .antMatchers(HttpMethod.POST, "/user/**").permitAll()
            .antMatchers(HttpMethod.GET, "/doctor/**").permitAll()
            .antMatchers(HttpMethod.POST, "/doctor/**").permitAll()
            .antMatchers(HttpMethod.GET, "/lab/**").permitAll()
            .antMatchers(HttpMethod.POST, "/lab/**").permitAll()
            .antMatchers(HttpMethod.GET, "/reception/**").permitAll()
            .antMatchers(HttpMethod.POST, "/reception/**").permitAll()
            .antMatchers(HttpMethod.GET, "/chemist/**").permitAll()
            .antMatchers(HttpMethod.POST, "/chemist/**").permitAll()
            .antMatchers(HttpMethod.GET, "/ambulance/**").permitAll()
            .antMatchers(HttpMethod.POST, "/ambulance/**").permitAll()
            .antMatchers(HttpMethod.GET, "/bloodbank/**").permitAll()
            .antMatchers(HttpMethod.POST, "/bloodbank/**").permitAll()
            .antMatchers(HttpMethod.GET, "/pm/**").permitAll()
            .antMatchers(HttpMethod.POST, "/pm/**").permitAll()
            .antMatchers(HttpMethod.GET, "/bpo/**").permitAll()
            .antMatchers(HttpMethod.POST, "/bpo/**").permitAll()
            .antMatchers(HttpMethod.GET, "/syndicate/**").permitAll()
            .antMatchers(HttpMethod.POST, "/syndicate/**").permitAll()
            .antMatchers(HttpMethod.GET, "/dropdown/get-country-lists").permitAll()
            .antMatchers(HttpMethod.POST, "/adminorganization/**").permitAll()
            .antMatchers(HttpMethod.GET, "/pathologist/**").permitAll()
            .antMatchers(HttpMethod.POST, "/pathologist/**").permitAll()
            .antMatchers(HttpMethod.GET, "/districtlevel/**").permitAll()
            .antMatchers(HttpMethod.POST, "/districtlevel/**").permitAll()
            .antMatchers(HttpMethod.GET, "/admin/**").permitAll()
            .antMatchers(HttpMethod.POST, "/admin/**").permitAll()
            .antMatchers(HttpMethod.GET, "/blocklevel/**").permitAll()
            .antMatchers(HttpMethod.POST, "/blocklevel/**").permitAll()
            .antMatchers(HttpMethod.GET, "/statelevel/**").permitAll()
            .antMatchers(HttpMethod.POST, "/statelevel/**").permitAll()
            .antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
            .antMatchers(HttpMethod.POST, "/api/change-password").permitAll()
            .antMatchers(HttpMethod.GET, "/api/get-role-api").permitAll()
            .antMatchers(HttpMethod.GET, "/api/get-version").permitAll()
            .antMatchers(HttpMethod.GET, "/api/get-assigned-block").permitAll()
            .antMatchers(HttpMethod.GET, "/api/get-blockdropdown-list").permitAll()
            .antMatchers(HttpMethod.GET, "/api/get-institution-list").permitAll()
            .antMatchers(HttpMethod.GET, "/api/get-catagory-list").permitAll()
            .antMatchers(HttpMethod.GET, "/api/get-agencydropdown-list").permitAll()
            .antMatchers(HttpMethod.GET, "/api/get-physicalStatusdropdown-list").permitAll()
            .antMatchers(HttpMethod.POST, "/api/post-project-status-upload").permitAll()
            .antMatchers(HttpMethod.GET, "/api/get-project-list").permitAll()
            .antMatchers(HttpMethod.GET, "/api/get-project-details-list").permitAll()
            .antMatchers(HttpMethod.GET, "/api/get-project-financial-details").permitAll()
            .antMatchers(HttpMethod.POST, "/api/post-Financial-status-upload").permitAll()
            .antMatchers(HttpMethod.GET, "/api/get-block-project-details").permitAll()
            .antMatchers(HttpMethod.GET, "/api/get-block-project-details-demo").permitAll()
            .antMatchers(HttpMethod.GET, "/api/get-mobile-version-list").permitAll()
            .antMatchers(HttpMethod.GET, "/util/getSessionByUsername").permitAll()
            .antMatchers(HttpMethod.POST, "/api/user-self-registration").permitAll()
            .antMatchers(HttpMethod.POST, "/api/login").permitAll()
            .antMatchers(HttpMethod.POST, "/api/login-with-otp").permitAll()
            // Deny all other requests
            .anyRequest().denyAll()
            .and()
            .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        // Hide server information in headers
        httpSecurity.headers()
                .xssProtection().and() // Enable XSS protection
                .contentTypeOptions().and() // Prevent MIME-type sniffing
                .frameOptions().deny() // Deny framing of your content
                .httpStrictTransportSecurity().includeSubDomains(true).maxAgeInSeconds(31536000).and() // Enforce HSTS
                .disable(); // Disable headers exposing server version

        // Add CSP header
        httpSecurity.headers().contentSecurityPolicy("default-src 'self'; " +
                "script-src 'self' 'unsafe-inline' 'unsafe-eval'; " +
                "style-src 'self' 'unsafe-inline'; " +
                "img-src 'self' data:;");
    }
}
