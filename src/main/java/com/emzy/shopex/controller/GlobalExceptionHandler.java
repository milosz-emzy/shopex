package com.emzy.shopex.controller;

import com.emzy.shopex.util.MapperUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
//lowest because it'll be last one called, default is LOWEST_PRECEDENCE
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalExceptionHandler {

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

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    protected ResponseEntity<Object> onConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        ErrorResponse errorResponse = MapperUtil.getErrorResponse(request, e.getMessage());

        return ResponseEntity.badRequest().body(errorResponse);
    }

}
