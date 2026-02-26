package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.Controller;

import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.DAO.AlumnoDAOImplementation;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.DAO.PaisDAOImplementation;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Alumno;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.ErroresArchivo;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Estado;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.ML.Result;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.Service.ValidationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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

    @Autowired
    private ValidationService validationService;

    @GetMapping //localhost:8080/alumno
    public String Index(Model model) {

        Result result = alumnoDAOImplementation.GetAll();
        model.addAttribute("alumnos", result.objects);
        model.addAttribute("alumnoBusqueda", new Alumno());
        return "AlumnoIndex";
    }

    @PostMapping
    public String Index(Model model, @ModelAttribute("alumnoBusqueda") Alumno alumnoBusqueda) {
//        Result result = alumnoDAOImplementation.GetAllBusqueda(alumnoBusqueda);
//        model.addAttribute("alumnos", result.objects);
//        model.addAttribute("alumnoBusqueda", alumnoBusqueda);
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

        if (bindingResult.hasErrors()) {
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
        } else if (imagenFile != null) {
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
            model.addAttribute("errorMessage", "El recurso no se pudo crear: " + result.errorMessage);
            model.addAttribute("alumno", alumno);
            return "AlumnoForm";
        }

    }

    @GetMapping("/cargamasiva")
    public String CargaMasiva() {
        return "CargaMasiva";
    }

    @PostMapping("/cargamasiva")
    public String CargaMasiva(@RequestParam("archivo") MultipartFile archivo, Model model, HttpSession session) {
        try {
            if (archivo != null) {

                String rutaBase = System.getProperty("user.dir");
                String rutaCarpeta = "src/main/resources/archivosCM";
                String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmSS"));
                String nombreArchivo = fecha + archivo.getOriginalFilename();
                String rutaArchivo = rutaBase + "/" + rutaCarpeta + "/" + nombreArchivo;
                String extension = archivo.getOriginalFilename().split("\\.")[1];
                List<Alumno> alumnos = null;
                if (extension.equals("txt")) {
                    archivo.transferTo(new File(rutaArchivo));
                    alumnos = LecturaArchivoTxt(new File(rutaArchivo));
                } else if (extension.equals("xlsx")) {
                    archivo.transferTo(new File(rutaArchivo));
                    alumnos = LecturaArchivoXLSX(new File(rutaArchivo));
                } else {
                    System.out.println("Extensión erronea, manda archivos del formato solicitado");
                }

                List<ErroresArchivo> errores = ValidarDatos(alumnos);

                if (errores.isEmpty()) {
//                    se guarda info -- no puedo mandar la ruta al front
                    model.addAttribute("errores", false);
                    //UUID
                    session.setAttribute("ruta", rutaArchivo);
//                    model.addAllAttributes("llaveRuta", "clave");
                } else {
//                    retorno lista errores, y la renderizo.
                    model.addAttribute("errores", true);
                }
                /*
                    - insertarlos
                    - renderizar la lista de errores
                 */
            }
        } catch (Exception ex) {
            // notificación de error

            System.out.println(ex.getLocalizedMessage());
        }
        return "CargaMasiva";
    }

    @GetMapping("/cargamasiva/procesar")
    public String ProcesarCargaMasiva(RedirectAttributes redirectAttributes, HttpSession session) {
        
        String rutaArchivo = session.getAttribute("ruta").toString();
        List<Alumno> alumnos = LecturaArchivoXLSX(new File(rutaArchivo));
        
        Result result = alumnoDAOImplementation.AddAll(alumnos); 
        /*Procesar
        Aperturar archivo
        Inertar datos
         */
        // mensaje de confirmación de carga exitosa
        return "redirect:/alumno";
    }

    /*
    Status  1 o 0 - NUMBER(1) - default 1
    */
    
    
    public List<Alumno> LecturaArchivoTxt(File archivo) {
        List<Alumno> alumnos;
        //try with reouces - Garbage collector
        try (InputStream inputStream = new FileInputStream(archivo); BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {

            alumnos = new ArrayList<>();
            String cadena = "";
            while ((cadena = bufferedReader.readLine()) != null) {
//                Nombre|ApellidoPaterno|Materno|Fecha
                String[] datosAlumno = cadena.split("\\|");
                Alumno alumno = new Alumno();
                alumno.setNombre(datosAlumno[0]);
                alumno.setApellidoPaterno(datosAlumno[1]);

                alumnos.add(alumno);
            }

        } catch (Exception ex) {
            return null;
        }

        return alumnos;
    }

    public List<Alumno> LecturaArchivoXLSX(File archivo) {
        List<Alumno> alumnos = null;

        try (InputStream inputStream = new FileInputStream(archivo); XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {

            XSSFSheet sheet = workbook.getSheetAt(0);

            alumnos = new ArrayList<>();

            for (Row row : sheet) {
                Alumno alumno = new Alumno();

                alumno.setNombre(row.getCell(0).toString());
                alumno.setApellidoPaterno(row.getCell(1).toString());

                alumnos.add(alumno);
            }

        } catch (Exception ex) {
        }

        return alumnos;
    }

    public List<ErroresArchivo> ValidarDatos(List<Alumno> alumnos) {
        List<ErroresArchivo> errores = new ArrayList<>();
        int fila = 0;
        for (Alumno alumno : alumnos) {
            BindingResult bindingResult = validationService.ValidateObject(alumno);
            fila++;
            if (bindingResult.hasErrors()) {
                for (ObjectError objectError : bindingResult.getAllErrors()) {
                    ErroresArchivo errorCarga = new ErroresArchivo();
//                    erroresArchivo.dato = objectError.getObjectName();
                    FieldError fieldError = (FieldError) objectError;
                    errorCarga.dato = fieldError.getField();
                    errorCarga.descripcion = fieldError.getDefaultMessage();
                    errorCarga.fila = fila;
                    errores.add(errorCarga);
                }
            }

        }

        return errores;
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
