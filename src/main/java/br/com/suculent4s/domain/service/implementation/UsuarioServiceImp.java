package br.com.suculent4s.domain.service.implementation;

import br.com.suculent4s.api.dto.TipoUsuarioDTO;
import br.com.suculent4s.domain.enums.TipoPessoaEnum;
import br.com.suculent4s.domain.enums.TipoUsuarioEnum;
import br.com.suculent4s.domain.exception.NegocioListException;
import br.com.suculent4s.domain.model.TipoUsuario;
import br.com.suculent4s.domain.model.Usuario;
import br.com.suculent4s.domain.repository.UsuarioRepository;
import br.com.suculent4s.domain.service.TipoUsuarioService;
import br.com.suculent4s.domain.service.UsuarioService;
import br.com.suculent4s.resources.util.VerifyIsExistTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario save(Usuario usuario) {
        List<String> errors = verifyFieldsSave (usuario);

        if(errors.isEmpty()) {
            List <TipoUsuario> tipoUsuarios = new ArrayList<>();

            usuario.setTipoPessoa(TipoPessoaEnum.valueOf(usuario.getTipoPessoa().toString().toUpperCase()));
            usuario.getTipoUsuario().stream()
                    .forEach(tipo -> {
                       tipoUsuarios.add(converterTipoToTipoDTO(getByTipoUsuario(tipo.getTipoUsuario().toString())));
                    });

            usuario.setTipoUsuario(tipoUsuarios);
            return usuarioRepository.save(usuario);
        } else {
            throw new NegocioListException(errors, "Validação de campos");
        }
    }

    /*method to verify and valid fields*/
    private List<String> verifyFieldsSave(Usuario usuario){
        List<String> errors = new ArrayList<>();

        Optional<Usuario> verifyCOCAndTipoPessoa = getCpfOuCnpjAndTipoPessoa(usuario.getCpfOuCnpj(), usuario.getTipoPessoa());
        Optional<Usuario> verifyEmail = getEmail(usuario.getEmail());

        if(!VerifyIsExistTypeEnum.isExistsEnum(usuario.getTipoPessoa(), TipoPessoaEnum.class)) {
            errors.add("Tipo de usuário é inválido. Por favor, consulte os tipos disponíveis.");
        } else {
            if(!verifyCOCAndTipoPessoa.isEmpty()) {
                errors.add("Cpf ou Cnpj já está cadastrado para esse usuário. Por favor, informe outro Cpf ou Cnpj.");
            }
        }
        if(!verifyEmail.isEmpty()) {
            errors.add("Esse email já pertece a outro usuário.");
        }
        usuario.getTipoUsuario().stream()
                .forEach(tipo -> {
                    if(!VerifyIsExistTypeEnum.isExistsEnum(tipo.getTipoUsuario(), TipoUsuarioEnum.class)){
                        errors.add("Tipo de usuário : {"+tipo.getTipoUsuario().toString()+"} inserido é inválido.");
                    }
                });
        return errors;
    }

    /*method to converter TipoUsuarioDTO to TipoUsuario*/
    private TipoUsuario converterTipoToTipoDTO(TipoUsuarioDTO dto) {
        return TipoUsuario.builder()
                .id(dto.getId())
                .tipoUsuario(dto.getTipoUsuario())
                .build();
    }

    /*method to find By Email*/
    private Optional<Usuario> getEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    /*method to find By cpfOuCnpj And TipoPessoa*/
    private Optional<Usuario> getCpfOuCnpjAndTipoPessoa(String cpfOuCnpj, TipoPessoaEnum tipoPessoa) {
        return usuarioRepository.findByCpfOuCnpjAndTipoPessoa(cpfOuCnpj, tipoPessoa);
    }

    /*method to service TipoUsuario*/
    private TipoUsuarioDTO getByTipoUsuario(String tipoUsuario) {
        return tipoUsuarioService.findByTipo(tipoUsuario);
    }
}
