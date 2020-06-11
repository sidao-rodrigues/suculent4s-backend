package br.com.suculent4s.domain.service.implementation;

import br.com.suculent4s.domain.enums.TipoUsuarioEnum;
import br.com.suculent4s.domain.exception.EntidadeNaoEncontradaException;
import br.com.suculent4s.domain.exception.ListaVaziaException;
import br.com.suculent4s.domain.model.TipoUsuario;
import br.com.suculent4s.domain.repository.TipoUsuarioRepository;
import br.com.suculent4s.domain.service.TipoUsuarioService;
import br.com.suculent4s.resources.util.VerifyIsExistTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TipoUsuarioServiceImp implements TipoUsuarioService {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Override
    public TipoUsuario findByTipo(String tipoUsuario){
        if(VerifyIsExistTypeEnum.isExistsString(tipoUsuario, Arrays.asList(TipoUsuarioEnum.values())))
        {
            return tipoUsuarioRepository.findByTipoUsuario(TipoUsuarioEnum.valueOf(tipoUsuario.toUpperCase())).get();
        } else {
            throw new EntidadeNaoEncontradaException("Tipo de usuário não existe");
        }
    }

    @Override
    public List<TipoUsuario> findAllTipo(){
        List<TipoUsuario> tipos = tipoUsuarioRepository.findAll();

        if(tipos.isEmpty()){
            throw new ListaVaziaException();
        }
        return tipos;
    }
}
