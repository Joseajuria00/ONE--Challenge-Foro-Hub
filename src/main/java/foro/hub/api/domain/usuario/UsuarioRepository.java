package foro.hub.api.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);
    Boolean existsByLogin(String login);
    @Query("""
            select u.status
            from Usuario u
            where
            u.login = :login
            """)
    boolean findStatusByLogin(String login);
}
