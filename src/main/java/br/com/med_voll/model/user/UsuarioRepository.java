package br.com.med_voll.model.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    UserDetails findByLogin(String login);
}
