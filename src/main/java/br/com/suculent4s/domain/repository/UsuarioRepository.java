package br.com.suculent4s.domain.repository;

import br.com.suculent4s.domain.enums.TipoPessoaEnum;
import br.com.suculent4s.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByCpfOuCnpjAndTipoPessoa(String cpfOuCnpj, TipoPessoaEnum tipoPessoa);

}
