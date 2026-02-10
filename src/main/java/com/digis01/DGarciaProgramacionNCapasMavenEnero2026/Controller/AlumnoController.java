package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.Controller;

import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.DAO.PaisDAOImplementation;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Alumno;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Result;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("alumno")
public class AlumnoController {

    // injection usuarioDAOIMplementation
    // injection paisDAOImplementation
    @Autowired
    private PaisDAOImplementation paisDAOImplementation;
    
    @GetMapping //localhost:8080/alumno
    public String Index(Model model){
        List<Alumno> alumnos = new ArrayList<>();
        alumnos.add(new Alumno(1, "Hernan", "Martinez", "Bruno", "5588996633", "hmartinez@email.com", new Date()));
        alumnos.add(new Alumno(2, "Fernando", "Cruz", "Velazquez", "5588996632", "fcruz@email.com", new Date()));
        alumnos.add(new Alumno(3, "Miguel Fernando", "Pastrana", "Adame", "5588996631", "mpastrana@email.com", new Date()));
        alumnos.add(new Alumno(4, "Diego", "Gomez", "Tagle", "5588996630", "dgomez@email.com", new Date()));
        
        model.addAttribute("alumnos", alumnos);
        return "AlumnoIndex";
    }
    
    
    @GetMapping("form")
    public String Accion (Model model){ // Model / inyecta el modelo en la vista
        model.addAttribute("alumno", new Alumno());
        Result resultPaises = paisDAOImplementation.GetAll();
        model.addAttribute("paises", resultPaises.objects);
//        model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
        return "AlumnoForm";
    }
    
    @PostMapping("form")
    public String Accion (@ModelAttribute("alumno") Alumno alumno){ // ModelAttribrute - Obtiene las modificaciones ocurridas en el modelo
        
//        Result result = alumnoDAOImplementation.Add(alumno);
        /* 
            1.GetAll
            2.Form
                .1 GET - Que la vista cargue todos los elementos.
                .2 POST - Recuperar todos los elemento 
            3 SQL
                .1 UsuarioDireccionAddSP
        
            TRANSACCION 
            Generar logica de inserción doble
                - inserta usuario 
                - inserta direccion 
                *NOTA : todo en el mismo SP*
        */
        
        return "AlumnoForm";
    }
    
}
