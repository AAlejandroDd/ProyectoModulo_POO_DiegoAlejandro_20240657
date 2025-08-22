package Diego_Alejandro_202406567.Diego_Alejandro_202406567.Entity;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@ToString @EqualsAndHashCode
@Table(name = "LIBROS")
public class EntityLibros {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_libro")
    @SequenceGenerator(sequenceName = "seq_libro", name = "seq_libro", allocationSize = 1)

    @Column(name = "ID")
    private Long id;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "ISBN")
    private String isbn;
    @Column(name = "AÑO_PUBLICACION")
    private int año_publicacion;
    @Column(name = "GENERO")
    private String genero;
    @Column(name = "AUTOR_ID")
    private Long autor_id;
}
