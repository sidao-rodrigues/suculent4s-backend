package br.com.suculent4s.domain.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class NegocioListException extends RuntimeException{

    @Getter
    @Setter
    private List<String> erros;

    public NegocioListException(String msg){
        super(msg);
    }

    public NegocioListException(List<String> listaErros, String msg) {
        super(msg);
        this.erros = listaErros;
    }
}
