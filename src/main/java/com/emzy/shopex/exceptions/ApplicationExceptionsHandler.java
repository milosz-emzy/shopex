package com.emzy.shopex.exceptions;

import com.emzy.shopex.util.MapperUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class ApplicationExceptionsHandler {

    private static final Logger log = LoggerFactory.getLogger(ApplicationExceptionsHandler.class);

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> onMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e,
                                                                               HttpServletRequest request) {
        MethodParameter parameter = e.getParameter();
        String message = "Parameter: '" + parameter.getParameterName() + "' is not valid. " + "Value '" + e.getValue()
                + "' could not be bound to type: '" + parameter.getParameterType()
                .getSimpleName()
                .toLowerCase()
                + "'";
        ErrorResponse errorResponse = MapperUtil.getErrorResponse(request, message);

        log.error(errorResponse.toString());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> onConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        ErrorResponse errorResponse = MapperUtil.getErrorResponse(request, e.getMessage());

        log.error(errorResponse.toString());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(value = ClientZipCodeNotFoundException.class)
    public ResponseEntity<ErrorResponse> onZipCodeNotFoundException(ClientZipCodeNotFoundException e, HttpServletRequest request) {
        ErrorResponse errorResponse = MapperUtil.getErrorResponse(request, e.getMessage(), HttpStatus.NOT_FOUND);

        log.error(errorResponse.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> onEntityNotFoundException(EntityNotFoundException e, HttpServletRequest request) {
        ErrorResponse errorResponse = MapperUtil.getErrorResponse(request, e.getMessage(), HttpStatus.NOT_FOUND);

        log.error(errorResponse.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}