package Diego_Alejandro_202406567.Diego_Alejandro_202406567.Repositories;

import Diego_Alejandro_202406567.Diego_Alejandro_202406567.Entity.EntityLibros;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface LibrosRepositories extends JpaRepository<EntityLibros, Long>{

    Page findAll(Pageable pageable);
}

