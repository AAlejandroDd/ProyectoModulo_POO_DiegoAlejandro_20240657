package Diego_Alejandro_202406567.Diego_Alejandro_202406567.Models.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class LibrosDTO {
    private Long id;
    @NotNull
    private String titulo;
    @NotNull
    private String isdn;
    @NotNull
    private int año_publicacion;
    @NotNull
    private String genero;
    @NotNull
    private Long autor_id;
}
