package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.Controller;

import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Alumno;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("alumno")
public class AlumnoController {

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
    public String Accion (Model model){
        return "AlumnoForm";
    }
}
