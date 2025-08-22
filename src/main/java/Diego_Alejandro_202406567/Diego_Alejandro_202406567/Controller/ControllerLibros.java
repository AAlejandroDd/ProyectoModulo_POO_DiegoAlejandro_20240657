package Diego_Alejandro_202406567.Diego_Alejandro_202406567.Controller;

import Diego_Alejandro_202406567.Diego_Alejandro_202406567.Exeptions.ExceptionLibros;
import Diego_Alejandro_202406567.Diego_Alejandro_202406567.Exeptions.ExceptionsColumnDuplicate;
import Diego_Alejandro_202406567.Diego_Alejandro_202406567.Models.Dto.LibrosDTO;
import Diego_Alejandro_202406567.Diego_Alejandro_202406567.Services.LibrosServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
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
    ) {
        if (size <= 0 || size > 50) {
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
        return ResponseEntity.ok(Autores);
    }

    @PostMapping("/newLibro")
    private ResponseEntity<Map<String, Object>> insertAutores(@Valid @RequestBody LibrosDTO json, HttpServletRequest request) {
        try {
            LibrosDTO response = services.insert(json);
            if (response == null) {
                return ResponseEntity.badRequest().body(Map.of(
                        "Error", "Inserción incorrecta",
                        "Estatus", "Inserción incorrecta",
                        "Descripción", "Verifique los valores"
                ));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "Estado", "Completado",
                    "data", response
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "error",
                    "message", "Error al registrar el autor",
                    "detail", e.getMessage()
            ));
        }
    }

    @PutMapping("/updateLibro/{id}")
    public ResponseEntity<?> modificarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody LibrosDTO usuario,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }

        try {
            LibrosDTO usuarioActualizado = services.update(id, usuario);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (ExceptionLibros e) {
            return ResponseEntity.notFound().build();
        } catch (ExceptionsColumnDuplicate e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    Map.of("error", "Datos duplicados", "campo", e.getColumnDuplicate())
            );
        }
    }

    // Mapea este metodo a una petición DELETE con un parámetro de ruta {id}
    @DeleteMapping("/deleteLibro/{id}")
    public ResponseEntity<Map<String, Object>> eliminarUsuario(@PathVariable Long id) {
        try {
            // Intenta eliminar la categoria usando objeto 'service'
            // Si el metodo delete retorna false (no encontró la categoria)
            if (!services.delete(id)) {
                // Retorna una respuesta 404 (Not Found) con información detallada
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        // Agrega un header personalizado
                        .header("X-Mensaje-Error", "Autor no encontrada")
                        // Cuerpo de la respuesta con detalles del error
                        .body(Map.of(
                                "error", "Not found",  // Tipo de error
                                "mensaje", "La Autor no ha sido encontrada"  // Mensaje descriptivo

                        ));
            }

            // Si la eliminación fue exitosa, retorna 200 (OK) con mensaje de confirmación
            return ResponseEntity.ok().body(Map.of(
                    "status", "Proceso completado",  // Estado de la operación
                    "message", "Autor eliminada exitosamente"  // Mensaje de éxito
            ));

        } catch (Exception e) {
            // Si ocurre cualquier error inesperado, retorna 500 (Internal Server Error)
            return ResponseEntity.internalServerError().body(Map.of(
                    "status", "Error",  // Indicador de error
                    "message", "Error al eliminar el error",  // Mensaje general
                    "detail", e.getMessage()  // Detalles técnicos del error (para debugging)
            ));
        }

    }
}
