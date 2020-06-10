package br.com.suculent4s.domain.model;

import br.com.suculent4s.domain.enums.TipoPessoa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String cpfOuCnpj;
    private TipoPessoa tipoPessoa;

}
