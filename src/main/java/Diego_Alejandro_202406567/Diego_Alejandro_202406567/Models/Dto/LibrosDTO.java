package Diego_Alejandro_202406567.Diego_Alejandro_202406567.Models.Dto;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private String titulo;
    @NotBlank
    private String isdn;
    @NotBlank
    private int año_publicacion;
    @NotBlank
    private String genero;
    @NotBlank
    private Long autor_id;

    @Positive(message = "ressdfd")
}
