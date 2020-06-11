package br.com.suculent4s.api.dto;

import br.com.suculent4s.domain.enums.TipoUsuarioEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoUsuarioDTO {

    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoUsuarioEnum tipoUsuario;

}
