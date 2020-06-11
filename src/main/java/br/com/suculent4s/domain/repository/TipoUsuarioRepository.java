package br.com.suculent4s.domain.repository;

import br.com.suculent4s.domain.enums.TipoUsuarioEnum;
import br.com.suculent4s.domain.model.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {

    Optional<TipoUsuario> findByTipoUsuario(TipoUsuarioEnum tipoUsuario);

}
