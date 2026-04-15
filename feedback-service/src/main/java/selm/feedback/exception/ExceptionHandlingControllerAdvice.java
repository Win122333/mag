package selm.feedback.exception;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class ExceptionHandlingControllerAdvice {
    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<ProblemDetail>> handleWebExchangeBindException(WebExchangeBindException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setProperty("errors", e.getAllErrors().stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .toList());
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail));
    }
}
