package br.com.suculent4s.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TipoUsuarioEnum {

    ADMIN,
    CLIENTE,
    VENDEDOR;

}
