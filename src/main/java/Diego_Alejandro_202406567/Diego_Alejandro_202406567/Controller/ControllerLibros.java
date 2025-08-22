package Diego_Alejandro_202406567.Diego_Alejandro_202406567.Controller;

import Diego_Alejandro_202406567.Diego_Alejandro_202406567.Models.Dto.LibrosDTO;
import Diego_Alejandro_202406567.Diego_Alejandro_202406567.Services.LibrosServices;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/Libros")
@CrossOrigin
public class ControllerLibros {

    @Autowired
    private LibrosServices services;

    @GetMapping("getAllLibros")
    private ResponseEntity<Page<LibrosDTO>> getDataLibros(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        if(size <= 0 || size > 50){
            ResponseEntity.badRequest().body(Map.of(
                    "status", "El tamaño de la pagina debe estar entre 1 y 50"
            ));
            return ResponseEntity.ok(null);
        }
        Page<LibrosDTO> Autores = services.getAllLibros(page, size);
        if (Autores == null) {
            ResponseEntity.badRequest().body(Map.of(
                    "status", "No hay Libros Registrados"
            ));
        }
    }

}
