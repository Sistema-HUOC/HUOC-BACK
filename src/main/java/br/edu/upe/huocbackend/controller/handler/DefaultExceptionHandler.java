package br.edu.upe.huocbackend.controller.handler;

import br.edu.upe.huocbackend.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(EntidadeUnicidadeVioladaException.class)
    public ResponseEntity<ErrorResponse> handleViolacaoUnicidade(EntidadeUnicidadeVioladaException e, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UserEmailInvalid.class)
    public ResponseEntity<String> handleUserEmailInvalid(UserEmailInvalid ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("E-mail inválido: " + ex.getMessage());
    }

    @ExceptionHandler(EnfermagemException.class)
    public ResponseEntity<String> handleUserEmailInvalid(EnfermagemException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }
    @ExceptionHandler(PesquisadorException.class)
    public ResponseEntity<String> handleUserEmailInvalid(PesquisadorException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }
    @ExceptionHandler(AdministradorException.class)
    public ResponseEntity<String> handleUserEmailInvalid(AdministradorException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }
    @ExceptionHandler(EspecializacaoException.class)
    public ResponseEntity<String> handleUserEmailInvalid(EspecializacaoException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }
    @ExceptionHandler(MedicoException.class)
    public ResponseEntity<String> handleUserEmailInvalid(MedicoException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }
}
