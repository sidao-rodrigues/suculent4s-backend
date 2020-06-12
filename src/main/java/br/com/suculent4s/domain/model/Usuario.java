package br.com.suculent4s.domain.model;

import br.com.suculent4s.domain.enums.TipoPessoaEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "{campo.usuario.nome}")
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @NotBlank(message = "{campo.usuario.email}")
    @Email
    @Column(name = "email", length = 60, nullable = false)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "{campo.usuario.senha}")
    @Column(name = "senha", nullable = false)
    private String senha;

    @NotBlank(message = "{campo.usuario.cpf-ou-cnpj}")
    @JsonProperty("cpf_ou_cnpj")
    @Column(name = "cpf_ou_cnpj", unique = true)
    private String cpfOuCnpj;

    //@NotBlank(message = "{campo.usuario.tipo-pessoa}")
    @Enumerated(EnumType.STRING)
    //@JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("tipo_pessoa")
    @Column(name = "tipo_pessoa")
    private TipoPessoaEnum tipoPessoa;

    @Valid
    @JsonProperty("tipo_usuario")
    @JsonIgnoreProperties(value = {"usuario"})
    @ManyToMany
    @JoinTable(name = "acesso_usuario",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_acesso_usuario"))
    private List<TipoUsuario> tipoUsuario;

}
