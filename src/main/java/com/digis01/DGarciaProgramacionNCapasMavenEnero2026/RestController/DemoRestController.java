package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.RestController;

//Spring Web

import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Alumno;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//- http

@RestController
@RequestMapping("api/demo")
public class DemoRestController {

    @GetMapping
    public String HolaMundo(){
        return "HolaMundo";
    }
    
    @GetMapping("saludo/{nombre}")
    public String Saludo(@PathVariable("nombre") String nombre){
        return "Hola " + nombre;
    }
    
    @GetMapping("saludo")
    public String Saludo2(@RequestParam("nombre") String nombre){
        return "Hola " + nombre;
    }
    
    @GetMapping("datos/{status}")
    public ResponseEntity ObtenerDatos(@PathVariable("status") int status){
        List<Alumno> alumnos = new ArrayList<>();
        alumnos.add(new Alumno(1, "Arturo", "Cardenas", "Rizo", "556632211", "acardenaz@gmail.com", null));
        alumnos.add(new Alumno(2, "Miguel", "Pastrana", "Adame", "556632212", "mpastrana@gmail.com", null));
        return ResponseEntity.status(status).body(alumnos);
    }
    
    /*
        GET
        ** suma de 2 elementos - GET
        ** suma de n elementos - POST / mandar un arreglo
        ** retornar la lista de usuarios - GET 
        ** investigar como cambiar de puerto un proyecto
        ** analizar proyecto, y decidir que es backend y que no es backend
        ** contruir clase con base en json 
        ** construir ejemplo json con base en ejemplo clase
    */
    
}
