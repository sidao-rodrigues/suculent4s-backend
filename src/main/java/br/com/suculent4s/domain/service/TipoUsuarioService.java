package br.com.suculent4s.domain.service;

import br.com.suculent4s.api.dto.TipoUsuarioDTO;

import java.util.List;

public interface TipoUsuarioService {

    List<TipoUsuarioDTO> findAllTipo();
    TipoUsuarioDTO findByTipo(String tipoUsuario);

}
