package br.com.suculent4s.domain.exception;

public class NegocioException extends RuntimeException{

    public NegocioException(String msg){
        super(msg);
    }

}
