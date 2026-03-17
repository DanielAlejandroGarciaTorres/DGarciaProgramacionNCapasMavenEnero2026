package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.Service;

import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.DAO.AlumnoDAOJPAImplementation;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Alumno;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Result;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailJPA implements UserDetailsService{
    
    private final AlumnoDAOJPAImplementation alumnoJPADAOImplementation;
    
    public UserDetailJPA( AlumnoDAOJPAImplementation alumnoJPADAOImplementation){
        this.alumnoJPADAOImplementation = alumnoJPADAOImplementation;
    }

    
    
    
    /*injection 
    field - campo / @autowired private entity enti;
    constructor 
    setter // prototype
    
    injection singlenton - unica instancia 
    injection prototype
    */

    @Override // recuperar la información de la bd / y gestionar que rol, que permisos tiene en el sistema
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Result result = alumnoJPADAOImplementation.GetByEmail(username);
        
        /*
        UserDetails
            - password coincide
            - status 0 - no da acceso
            - rol - indica o almacena para gestionar niveles de permiso
            - 
        */
        Alumno alumno = (Alumno) result.object;
        return User.withUsername(alumno.getNombre())
                .password(alumno.getPassword())
                .disabled(false) // (alumno.getStatus() == 0) ? true : false
                .build();
    }
    
}
