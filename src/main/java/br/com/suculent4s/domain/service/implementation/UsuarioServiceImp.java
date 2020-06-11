package br.com.suculent4s.domain.service.implementation;

import br.com.suculent4s.domain.enums.TipoPessoaEnum;
import br.com.suculent4s.domain.model.Usuario;
import br.com.suculent4s.domain.repository.UsuarioRepository;
import br.com.suculent4s.domain.service.TipoUsuarioService;
import br.com.suculent4s.domain.service.UsuarioService;
import br.com.suculent4s.resources.util.VerifyIsExistTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    /*method to verify and valid fields*/
    private List<String> verifyFieldsSave(Usuario usuario){
        List<String> errors = new ArrayList<>();

        if(!VerifyIsExistTypeEnum.isExistsEnum(usuario.getTipoPessoa(), TipoPessoaEnum.class)) {
            errors.add("Tipo de usuário é inválido. Por favor, consulte os tipos disponíveis.");
        }
        //if(usuario.ge)
        return null;
    }
}
