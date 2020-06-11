package br.com.suculent4s.domain.model;

import br.com.suculent4s.domain.enums.TipoUsuarioEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
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
    @NotBlank(message = "{campo.tipo-usuario.tipo}")
    private TipoUsuarioEnum tipoUsuario;

    @JsonIgnoreProperties(value = {"tipoUsuario"})
    @ManyToMany(mappedBy = "tipoUsuario")
    private List<Usuario> usuario;
}
