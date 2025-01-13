package foro.hub.api.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findByStatusTrue(Pageable pageable);
    @Query("""
            select t.status
            from Topico t
            where
            t.id = :idTopico
            """)
    boolean findStatusById(Long idTopico);

    boolean existsByTitulo(String titulo);
    boolean existsByMensaje(String mensaje);
}
