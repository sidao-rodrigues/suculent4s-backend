package br.com.suculent4s.domain.service.implementation;

import br.com.suculent4s.api.dto.TipoUsuarioDTO;
import br.com.suculent4s.domain.enums.TipoUsuarioEnum;
import br.com.suculent4s.domain.model.TipoUsuario;
import br.com.suculent4s.domain.repository.TipoUsuarioRepository;
import br.com.suculent4s.domain.service.TipoUsuarioService;
import br.com.suculent4s.resources.util.VerifyIsExistTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoUsuarioServiceImp implements TipoUsuarioService {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Override
    public TipoUsuarioDTO findByTipo(String tipoUsuario){
        if(VerifyIsExistTypeEnum.isExistsString(tipoUsuario, TipoUsuarioEnum.class)) {
            return convertTipoUsuarioToDTO(
                    tipoUsuarioRepository.findByTipoUsuario(
                            TipoUsuarioEnum.valueOf(tipoUsuario.toUpperCase())).get());
        }
        return null;
    }

    @Override
    public List<TipoUsuarioDTO> findAllTipo(){
        return tipoUsuarioRepository.findAll().stream()
                .map(tipoUsuario -> {
                    return convertTipoUsuarioToDTO(tipoUsuario);
                }).collect(Collectors.toList());
    }

    /*method to converter tipoUsuario to TipoUsuarioDTO*/
    private TipoUsuarioDTO convertTipoUsuarioToDTO(TipoUsuario tipoUsuario){
        return TipoUsuarioDTO.builder()
                .id(tipoUsuario.getId())
                .tipoUsuario(tipoUsuario.getTipoUsuario())
                .build();
    }

}
