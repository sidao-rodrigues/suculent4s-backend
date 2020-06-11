package br.com.suculent4s.domain.model;

import br.com.suculent4s.domain.enums.TipoUsuarioEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario")
    private TipoUsuarioEnum tipoUsuario;

    @JsonIgnoreProperties(value = {"tipoUsuario"})
    @ManyToMany(mappedBy = "tipoUsuario")
    private Usuario usuario;
}
