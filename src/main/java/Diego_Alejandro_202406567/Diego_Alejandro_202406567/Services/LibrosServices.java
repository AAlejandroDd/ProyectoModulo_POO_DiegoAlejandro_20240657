package Diego_Alejandro_202406567.Diego_Alejandro_202406567.Services;

import Diego_Alejandro_202406567.Diego_Alejandro_202406567.Entity.EntityLibros;
import Diego_Alejandro_202406567.Diego_Alejandro_202406567.Exeptions.ExceptionLibros;
import Diego_Alejandro_202406567.Diego_Alejandro_202406567.Exeptions.ExceptionNotFound;
import Diego_Alejandro_202406567.Diego_Alejandro_202406567.Models.Dto.LibrosDTO;
import Diego_Alejandro_202406567.Diego_Alejandro_202406567.Repositories.LibrosRepositories;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Slf4j
@Service
public class LibrosServices {

    @Autowired
    private LibrosRepositories repo;

    public Page<LibrosDTO> getAllLibros(int page, int size) {
        Pageable pageable = (Pageable) PageRequest.of(page, size);
        Page<EntityLibros> pageEntity = (Page<EntityLibros>) repo.findAll(pageable);
        return pageEntity.map(this::ConvertirADTO);
    }

    private LibrosDTO ConvertirADTO(EntityLibros entityLibros) {
    }


    public LibrosDTO insert (@Valid LibrosDTO json) {
        if (json == null){
            throw new IllegalArgumentException("La informacion del libro no puede ser nula");
        }
        try{
            EntityLibros objData = ConvertirAEntity(json);
            EntityLibros LibroGuardado = repo.save(objData);
            return ConvertirADTO(LibroGuardado);
        } catch (Exception e){
            log.error("error al registrar el libro" + e.getMessage());
            throw new ExceptionLibros("El libro no pudo ser registrado");
        }
    }



    public LibrosDTO update (Long id, @Valid LibrosDTO json){
        EntityLibros libroExistente = repo.findById(id).orElseThrow(()-> new ExceptionNotFound("Libro No encontrado"));

        libroExistente.setTitulo(json.getTitulo());
        libroExistente.setIsbn(json.getIsdn());
        libroExistente.setAño_publicacion(json.getAño_publicacion());
        libroExistente.setGenero(json.getGenero());
        libroExistente.setAutor_id(json.getAutor_id());

        return ConvertirADTO(LibroActualizado);
    }

    public boolean delete(Long id){
        EntityLibros existencia = repo.findById(id).orElse(null);

        if (existencia != null){
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    private LibrosDTO(EntityLibros objEntity){
        LibrosDTO dto = new LibrosDTO();
        dto.setId(objEntity.getId());
        dto.setTitulo(objEntity.getTitulo());
        dto.setIsdn(objEntity.getIsbn());
        dto.setAño_publicacion(objEntity.getAño_publicacion());
        dto.setGenero(objEntity.getGenero());
        dto.setAutor_id(objEntity.getAutor_id());
        return dto;
    }

    private EntityLibros ConvertirAEntity(@Valid LibrosDTO json){
        EntityLibros entity = new EntityLibros();
        entity.setTitulo(json.getTitulo());
        entity.setIsbn(json.getIsdn());
        entity.setAño_publicacion(json.getAño_publicacion());
        entity.setGenero(json.getGenero());
        entity.setAutor_id(json.getAutor_id());
        return entity;
    }


}
