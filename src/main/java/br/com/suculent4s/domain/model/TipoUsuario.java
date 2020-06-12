package br.com.suculent4s.domain.model;

import br.com.suculent4s.domain.enums.TipoUsuarioEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "tipo_usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    //@JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("tipo")
    @Column(name = "tipo_usuario")
    //@NotBlank(message = "{campo.tipo-usuario.tipo}")
    private TipoUsuarioEnum tipoUsuario;

    @JsonIgnoreProperties(value = {"tipoUsuario"})
    @ManyToMany(mappedBy = "tipoUsuario")
    private List<Usuario> usuario;
}
