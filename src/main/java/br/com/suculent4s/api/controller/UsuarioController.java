package br.com.suculent4s.api.controller;

import br.com.suculent4s.api.dto.TipoUsuarioDTO;
import br.com.suculent4s.domain.model.Usuario;
import br.com.suculent4s.domain.service.TipoUsuarioService;
import br.com.suculent4s.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Usuario saveNewUsuario(@RequestBody @Valid Usuario usuario){
        return usuarioService.save(usuario);
    }

    //apenas para teste poderá ser removido
    /*@GetMapping("/{tipo}")
    public TipoUsuarioDTO lista(@PathVariable("tipo") String tipo){
        return tipoUsuarioService.findByTipo(tipo);
    }

    @GetMapping
    public List<TipoUsuarioDTO> listaTipo(){
        return tipoUsuarioService.findAllTipo();
    }*/

}
