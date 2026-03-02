package sel.catalogue.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.BindException;
@Slf4j
@ControllerAdvice
public class BadRequestControllerAdvice {
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ProblemDetail> getExc(org.springframework.validation.BindException exception) {
        log.error("Ошибка с BindingException");
        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST, "error 400");
        problemDetail.setProperty("errors",
                exception.getAllErrors().stream()
                        .map(ObjectError::getDefaultMessage)
                        .toList());
        return ResponseEntity.badRequest()
                .body(problemDetail);
    }
}
