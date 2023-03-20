package br.gama.itau.projetofinal2.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundException(NotFoundException ex) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .titulo("Erro na solicitacao")
                .mensagem(ex.getMessage())
                .codigoStatus(HttpStatus.NOT_FOUND.value())
                .build();
                
        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundException(InternalServerErrorException ex) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .titulo("Qual é o motivo para cadastrar?")
                .mensagem(ex.getMessage())
                .codigoStatus(HttpStatus.NOT_FOUND.value())
                .build();
        
        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }
}
