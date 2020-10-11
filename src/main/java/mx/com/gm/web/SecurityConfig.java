package mx.com.gm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    /**
     * Metodo Bean que devuelve un BCryptPasswordEncoder
     * @return 
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    /**
     * Metodo para indidcar que vamos usar userDetailsService y el tipo de codificacion (passwordEncoder())
     * @param build 
     */
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception{
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

//    /**
//     * Metodo para la Autentificacion, que crea objetos en memoria
//     * @param auth
//     * @throws Exception 
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                    .password("{noop}123")
//                    .roles("ADMIN","USER")
//                    .and()
//                        .withUser("user")
//                        .password("{noop}123")
//                        .roles("USER");
//    }
    /**
     * Metodo para la autorizacion y página de error 403
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/editar/**", "/agregar/**", "/eliminar")
                .hasRole("ADMIN")
                .antMatchers("/")
                .hasAnyRole("USER", "ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/errores/403");
    }
}
