package com.devsuperior.bds04.controllers.exceptions;

import java.time.Instant;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationError> validationException(
    MethodArgumentNotValidException e,
    HttpServletRequest request
  ) {
    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
    ValidationError err = new ValidationError();
    err.setTimestamp(Instant.now());
    err.setStatus(status.value());
    err.setError("Validation exception");
    err.setMessage(e.getMessage());
    err.setPath(request.getRequestURI());

    for (FieldError f : e.getBindingResult().getFieldErrors()) {
      err.addError(f.getField(), f.getDefaultMessage());
    }

    return ResponseEntity.status(status).body(err);
  }

  // handle bad JSON formats in PUT/POST messages (not throwable)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<StandardError> jsonError(
    RuntimeException e,
    HttpServletRequest request
  ) {
    String error = "I was not able to understand HTTP message body.";
    HttpStatus status = HttpStatus.BAD_REQUEST;
    StandardError err = new StandardError(
      Instant.now(),
      status.value(),
      error,
      e.getMessage(),
      request.getRequestURI()
    );
    return ResponseEntity.status(status).body(err);
  }

  // handle bad HTTP parameters (not throwable)
  @ExceptionHandler(PropertyReferenceException.class)
  public ResponseEntity<StandardError> propertyReference(
    PropertyReferenceException e,
    HttpServletRequest request
  ) {
    String error = "I was not able to understand HTTP request parameters.";
    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
    StandardError err = new StandardError(
      Instant.now(),
      status.value(),
      error,
      e.getMessage(),
      request.getRequestURI()
    );
    return ResponseEntity.status(status).body(err);
  }
}
