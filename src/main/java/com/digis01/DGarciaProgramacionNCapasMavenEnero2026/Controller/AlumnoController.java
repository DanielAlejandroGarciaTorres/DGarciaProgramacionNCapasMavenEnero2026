package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.Controller;

import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.DAO.AlumnoDAOImplementation;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.DAO.PaisDAOImplementation;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Alumno;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Estado;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Result;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("alumno")
public class AlumnoController {

    // injection usuarioDAOIMplementation
    // injection paisDAOImplementation
    @Autowired
    private AlumnoDAOImplementation alumnoDAOImplementation;
    
    @Autowired
    private PaisDAOImplementation paisDAOImplementation;
    
    @GetMapping //localhost:8080/alumno
    public String Index(Model model){
        
        Result result = alumnoDAOImplementation.GetAll();
        model.addAttribute("alumnos", result.objects);
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
    
    
    
    
    @GetMapping("getEstadosByPais/{IdPais}")
    @ResponseBody // el metodo retorna un dato estructurado (JSON) y no una vista
    public Result GetEstadosByPais(@PathVariable("IdPais") int IdPais){
//        estadoDAOImplementation.getEstadosByPais(IdPais);
        Result result = new Result();
        result.objects = new ArrayList<>();
        result.objects.add(new Estado(1,"CDMX"));
        result.objects.add(new Estado(2,"Veracruz"));
        result.objects.add(new Estado(3,"Esto de México"));
        result.correct = true;
        
        return result;
    }
    
    
}
