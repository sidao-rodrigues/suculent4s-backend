package br.com.suculent4s.api.controller;

import br.com.suculent4s.domain.enums.TipoUsuarioEnum;
import br.com.suculent4s.domain.model.TipoUsuario;
import br.com.suculent4s.domain.model.Usuario;
import br.com.suculent4s.domain.service.TipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Usuario saveNewUsuario(@RequestBody @Valid Usuario usuario){
        return null;
    }

    @GetMapping("/{tipo}")
    public TipoUsuario lista(@PathVariable("tipo") String tipo){
        return tipoUsuarioService.findByTipo(tipo);
    }

}
