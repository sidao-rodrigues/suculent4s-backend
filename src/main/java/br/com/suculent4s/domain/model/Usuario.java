package br.com.suculent4s.domain.model;

import br.com.suculent4s.domain.enums.TipoPessoaEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @NotBlank
    @Email
    @Column(name = "email", length = 60, nullable = false)
    private String email;

    @NotBlank
    @Column(name = "cpf_ou_cnpj", unique = true)
    private String cpfOuCnpj;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pessoa")
    private TipoPessoaEnum tipoPessoa;

    @JsonIgnoreProperties(value = {"usuario"})
    @ManyToMany
    @JoinTable(name = "acesso_usuario",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_acesso_usuario"))
    private TipoUsuario tipoUsuario;

}
