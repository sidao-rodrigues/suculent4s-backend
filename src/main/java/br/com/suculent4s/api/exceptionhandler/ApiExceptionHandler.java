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
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;

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

        var problema = new Problema();
        problema.setStatus(status.value());
        problema.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
        problema.setDataHora(DateTimeConverter.converterDateTimeDefaultFormat(OffsetDateTime.now()));
        problema.setCampos(campos);

        return super.handleExceptionInternal(exception, problema, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        String title = "Unexpected error";
        String nome = "Malformed JSON request";

        var problema = Problema.builder()
                .status(status.value())
                .titulo(title)
                .dataHora(DateTimeConverter.converterDateTimeDefaultFormat(OffsetDateTime.now()))
                .campos(Arrays.asList(new Problema.Campo(nome, exception.getLocalizedMessage())))
                .build();

        return super.handleExceptionInternal(exception, problema, headers, status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Object> hanblerEntidadeNaoEncontrada(EntidadeNaoEncontradaException exception,
                                                               WebRequest request){
        var status = HttpStatus.NOT_FOUND;

        var problema = new Problema();
        problema.setStatus(status.value());
        problema.setTitulo(exception.getMessage());
        problema.setDataHora(DateTimeConverter.converterDateTimeDefaultFormat(OffsetDateTime.now()));

        return handleExceptionInternal(exception, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handlerNegocio(NegocioException exception, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;

        var problema = new Problema();
        problema.setStatus(status.value());
        problema.setTitulo(exception.getMessage());
        problema.setDataHora(DateTimeConverter.converterDateTimeDefaultFormat(OffsetDateTime.now()));

        return handleExceptionInternal(exception, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioListException.class)
    public ResponseEntity<Object> handlerNegocioList(NegocioListException exception, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;

        var problemas = new Problema();
        problemas.setStatus(status.value());
        problemas.setTitulo(exception.getMessage());
        problemas.setDataHora(DateTimeConverter.converterDateTimeDefaultFormat(OffsetDateTime.now()));
        problemas.setErros(exception.getErros());

        return handleExceptionInternal(exception, problemas, new HttpHeaders(), status, request);

    }

    @ExceptionHandler(ListaVaziaException.class)
    public ResponseEntity<Object> handlerListaVazia(ListaVaziaException exception, WebRequest request) {
        var status = HttpStatus.NO_CONTENT;
        return handleExceptionInternal(exception, null, new HttpHeaders(), status, request);
    }
}
