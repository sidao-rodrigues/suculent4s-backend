package br.com.suculent4s.api.exceptionhandler;

import br.com.suculent4s.domain.exception.EntidadeNaoEncontradaException;
import br.com.suculent4s.domain.exception.ListaVaziaException;
import br.com.suculent4s.domain.exception.NegocioException;
import br.com.suculent4s.domain.exception.NegocioListException;
import br.com.suculent4s.resources.util.DateTimeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                            HttpHeaders headers, HttpStatus status, WebRequest request) {

        var campos = new ArrayList<Problema.Campo>();

        for(ObjectError error : exception.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField();
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            campos.add(new Problema.Campo(nome, mensagem));
        }

        var problema = Problema.builder()
                .status(status.value())
                .titulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
                .dataHora(DateTimeConverter.converterDateTimeDefaultFormat(OffsetDateTime.now()))
                .campos(campos)
                .build();
        return super.handleExceptionInternal(exception, problema, headers, status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Object> hanblerEntidadeNaoEncontrada(EntidadeNaoEncontradaException exception,
                                                               WebRequest request){
        var status = HttpStatus.NOT_FOUND;

        var problema = Problema.builder()
                .status(status.value())
                .titulo(exception.getMessage())
                .dataHora(DateTimeConverter.converterDateTimeDefaultFormat(OffsetDateTime.now()))
                .build();
        return handleExceptionInternal(exception, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handlerNegocio(NegocioException exception, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;

        var problema = Problema.builder()
                .status(status.value())
                .titulo(exception.getMessage())
                .dataHora(DateTimeConverter.converterDateTimeDefaultFormat(OffsetDateTime.now()))
                .build();
        return handleExceptionInternal(exception, problema, new HttpHeaders(), status, request);

    }

    @ExceptionHandler(NegocioListException.class)
    public ResponseEntity<Object> handlerNegocioList(NegocioListException exception, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;

        var problemas = Problema.builder()
                .status(status.value())
                .titulo(exception.getMessage())
                .dataHora(DateTimeConverter.converterDateTimeDefaultFormat(OffsetDateTime.now()))
                .erros(exception.getErros())
                .build();
        return handleExceptionInternal(exception, problemas, new HttpHeaders(), status, request);

    }

    @ExceptionHandler(ListaVaziaException.class)
    public ResponseEntity<Object> handlerListaVazia(ListaVaziaException exception, WebRequest request) {
        var status = HttpStatus.NO_CONTENT;
        return handleExceptionInternal(exception, null, new HttpHeaders(), status, request);
    }
}
