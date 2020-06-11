package br.com.suculent4s.domain.service;

import br.com.suculent4s.domain.enums.TipoUsuarioEnum;
import br.com.suculent4s.domain.model.TipoUsuario;

import java.util.List;

public interface TipoUsuarioService {

    List<TipoUsuario> findAllTipo();
    TipoUsuario findByTipo(String tipoUsuario);

}
