package com.zara.price.controller;

import com.zara.price.controller.dto.ApiError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

import static org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiError> noHandlerFoundException(HttpServletRequest req, NoHandlerFoundException ex) {
        ApiError apiError = ApiError.builder()
                .error("route_not_found")
                .message(String.format("Route %s not found", req.getRequestURI()))
                .status(NOT_FOUND.value())
                .build();
        return handleErrorResponse(ex, apiError);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> handlerHttpRequestMethodNotSupportedException(HttpServletRequest req, HttpRequestMethodNotSupportedException ex) {
        ApiError apiError = ApiError.builder()
                .error("method_not_allowed")
                .message(String.format("%s. Route %s", ex.getMessage(), req.getRequestURI()))
                .status(METHOD_NOT_ALLOWED.value())
                .build();
        return handleErrorResponse(ex, apiError);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handlerHttpMessageNotReadableException(HttpServletRequest req, HttpMessageNotReadableException ex) {
        ApiError apiError = ApiError.builder()
                .error("required_request_body")
                .message("Required request body is missing")
                .status(BAD_REQUEST.value())
                .build();
        return handleErrorResponse(ex, apiError);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiError> handleMissingRequestParameterException(MissingServletRequestParameterException ex) {
        ApiError apiError = ApiError.builder()
                .error("missing_parameter")
                .message(String.format("Missing required parameter '%s'", ex.getParameterName()))
                .status(BAD_REQUEST.value())
                .build();
        return handleErrorResponse(ex, apiError);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiError> handleUnknownException(Exception ex) {
        ApiError apiError = ApiError
                .builder()
                .error("internal_error")
                .message("Internal server error")
                .status(INTERNAL_SERVER_ERROR.value())
                .build();
        return handleErrorResponse(ex, apiError);
    }

    protected ResponseEntity<ApiError> handleErrorResponse(Exception e, ApiError apiError) {
        logException(e);
        return ResponseEntity.status(apiError.getStatus())
                .body(apiError);
    }

    protected void logException(Exception e) {
        log.error(String.format("%s - %s", e.getMessage(), getFullStackTrace(e)));
    }
}
