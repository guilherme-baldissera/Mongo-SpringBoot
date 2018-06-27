package com.daitangroup.mysql.exception;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static String BAD_REQUEST = "Invalid Request";
    private static String CONSTRAINT_VIOLATION = "Constraint violation";


    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleArgumentNotValidException(ConstraintViolationException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), CONSTRAINT_VIOLATION,
                request.getDescription(false));

        System.out.println(ex.getMessage());
        ex.printStackTrace(System.err);

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDefinitionException.class)
    public final ResponseEntity<Object> handleArgumentNotValidException(InvalidDefinitionException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), BAD_REQUEST,
                request.getDescription(false));

        System.out.println(ex.getMessage());
        ex.printStackTrace(System.err);

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public final ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));

        System.out.println(ex.getMessage());
        ex.printStackTrace(System.err);

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));

        System.out.println(ex.getMessage());
        ex.printStackTrace(System.err);

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserIdMissingException.class)
    public final ResponseEntity<Object> handleUserIdMissingxception(UserIdMissingException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));

        System.out.println(ex.getMessage());
        ex.printStackTrace(System.err);

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), BAD_REQUEST,
                request.getDescription(false));

        System.out.println(ex.getMessage());
        ex.printStackTrace(System.err);

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
