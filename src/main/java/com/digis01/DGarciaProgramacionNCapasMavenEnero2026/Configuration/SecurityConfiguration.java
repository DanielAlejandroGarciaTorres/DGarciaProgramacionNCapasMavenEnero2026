package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.Configuration;

import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.Service.UserDetailJPA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
     
        /*Usar un login personalizado (hasta que todo, todoooooo quede funcional)*/
        http.authorizeHttpRequests(configurer -> configurer
                .requestMatchers("/alumno/**")
                .hasAnyRole("1er Semestre", "2do Semestre")
                .anyRequest().authenticated())
                .formLogin(form -> form
                        .defaultSuccessUrl("/alumno")
                )
                .userDetailsService(userDetailJPA);
                
        
        return http.build();          
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
//        return new NoOpPasswordEncoder();
    }

}
