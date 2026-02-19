package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.Controller;

import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.DAO.AlumnoDAOImplementation;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.DAO.PaisDAOImplementation;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Alumno;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Estado;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Result;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String Index(Model model) {

        Result result = alumnoDAOImplementation.GetAll();
        model.addAttribute("alumnos", result.objects);
        return "AlumnoIndex";
    }

    @GetMapping("form")
    public String Accion(Model model) { // Model / inyecta el modelo en la vista
        model.addAttribute("alumno", new Alumno());
        Result resultPaises = paisDAOImplementation.GetAll();
        model.addAttribute("paises", resultPaises.objects);
//        model.addAttribute("paises", paisDAOImplementation.GetAll().objects);
        return "AlumnoForm";
    }

    @PostMapping("form")
    public String Accion(@Valid @ModelAttribute("alumno") Alumno alumno, 
            BindingResult bindingResult, 
            @RequestParam("imagenFile") MultipartFile imagenFile,
            Model model,
            RedirectAttributes redirectAttributes) { // ModelAttribrute - Obtiene las modificaciones ocurridas en el modelo

        if(bindingResult.hasErrors()){
            model.addAttribute("alumno", alumno);
            return "AlumnoForm";
        }
        
        String nombreArchivo = imagenFile.getOriginalFilename(); //Nxus.png / personita.jpg
        //1. Expresión regular 
        //2. Cortar la palabra
        String[] cadena = nombreArchivo.split("\\.");
        if (cadena[1].equals("jpg") || cadena[1].equals("png")) {
            //convierto imagen a base 64, y la cargo en el modelo alumno 
            System.out.println("Imagen");
            try {
                // realizar la conversión de imagen a base 64;
                byte[] arregloBytes = imagenFile.getBytes();
                String base64Img = Base64.getEncoder().encodeToString(arregloBytes);
                alumno.setImagen(base64Img);
            } catch (Exception ex) {
                return "AlumnoForm";
            }
        } else if (imagenFile != null){
            //retorno error de archivo no permititido y regreso a formulario 
            System.out.println("Error");
        }
        System.out.println("Agregar");
        //proceso de agregar datos y retorno a vista de todos los usuarios
        Result result = alumnoDAOImplementation.Add(alumno); 
        
        if (result.correct) {
            redirectAttributes.addFlashAttribute("successMesage", "El recurso se agrego de forma correcta");
            return "redirect:/alumno";
        } else {
            model.addAttribute("errorMessage", "El recurso no se pudo crear: " + result.errorMessage );
            model.addAttribute("alumno", alumno);
            return "AlumnoForm";
        }
        
        
    }

    @GetMapping("getEstadosByPais/{IdPais}")
    @ResponseBody // el metodo retorna un dato estructurado (JSON) y no una vista
    public Result GetEstadosByPais(@PathVariable("IdPais") int IdPais) {
//        estadoDAOImplementation.getEstadosByPais(IdPais);
        Result result = new Result();
        result.objects = new ArrayList<>();
        result.objects.add(new Estado(1, "CDMX"));
        result.objects.add(new Estado(2, "Veracruz"));
        result.objects.add(new Estado(3, "Esto de México"));
        result.correct = true;

        return result;
    }

}
