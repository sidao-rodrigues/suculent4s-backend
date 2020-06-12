package br.com.suculent4s.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "data_hora", "titulo", "status", "erros" })
public class Problema {

    @JsonProperty("data_hora")
    private OffsetDateTime dataHora;
    private String titulo;
    private Integer status;
    private List<Campo> campos;
    private List<String> erros;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Campo {
        private String nome;
        private String mensagem;
    }

}
