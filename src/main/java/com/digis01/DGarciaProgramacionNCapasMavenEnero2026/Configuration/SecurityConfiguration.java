package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.Configuration;

import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.Service.UserDetailJPA;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserDetailJPA userDetailJPA;

    public SecurityConfiguration(UserDetailJPA userDetailJPA) {
        this.userDetailJPA = userDetailJPA;
        
    }

    // 1 validar que rutas o endpoints necesitan un proceso de seguridad
    // Almacena la sesión del usuario una vez logueado 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        /*Usar un login personalizado (hasta que todo, todoooooo quede funcional)*/
        http.authorizeHttpRequests(configurer -> configurer
                .requestMatchers("/login").permitAll()
                .requestMatchers("/alumno/**")
                .hasAnyRole("1er Semestre", "2do Semestre")
                .anyRequest().authenticated())
                .formLogin(form -> form
                /*
                        GET - que vista carga cuando no esta logueado
                        POST - que accion detona la información de formulario 
                 */
                .loginPage("/login") // Tu ruta personalizada
                .loginProcessingUrl("/login") // URL donde apunta el POST del form
                .defaultSuccessUrl("/alumno")
                .failureUrl("/login?error=true")
                )
                .userDetailsService(userDetailJPA);
        //JSESSIONID
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
//        return new NoOpPasswordEncoder();
    }
}
