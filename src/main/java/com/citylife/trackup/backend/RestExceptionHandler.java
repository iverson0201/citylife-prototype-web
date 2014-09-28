package com.citylife.trackup.backend;

import java.util.Arrays;

import javax.xml.bind.ValidationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.citylife.trackup.backend.domain.result.RestError;
import com.citylife.trackup.backend.exception.ExistException;
import com.citylife.trackup.backend.exception.NotAuthException;
import com.citylife.trackup.backend.exception.NotFoundException;
import com.citylife.trackup.backend.exception.NotImplementedException;
import com.citylife.trackup.backend.exception.OperateFailedException;
import com.codahale.metrics.annotation.ExceptionMetered;

import cz.jirutka.rsql.parser.ParseException;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月19日 下午4:35:18
 * 
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value={
            IllegalArgumentException.class,
            ValidationException.class,
            NotFoundException.class,
            NotImplementedException.class,
            AuthenticationException.class,
            OperateFailedException.class,
            NotAuthException.class,
            ParseException.class,
            ExistException.class
    })
    public ResponseEntity<Object> exceptionHandler(Exception ex, WebRequest request) {

        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof IllegalArgumentException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return handleException((IllegalArgumentException) ex, headers, status, request);
        }
        else if (ex instanceof ValidationException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return handleException((ValidationException) ex, headers, status, request);
        }
        else if (ex instanceof NotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            return handleException((NotFoundException) ex, headers, status, request);
        }
        else if (ex instanceof NotImplementedException) {
            HttpStatus status = HttpStatus.NOT_IMPLEMENTED;
            return handleException((NotImplementedException) ex, headers, status, request);
        }
        else if (ex instanceof OperateFailedException) {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleException((OperateFailedException) ex, headers, status, request);
        }
        else if (ex instanceof AuthenticationException) {
            HttpStatus status = HttpStatus.NETWORK_AUTHENTICATION_REQUIRED;
            return handleException((AuthenticationException) ex, headers, status, request);
        } else if (ex instanceof NotAuthException) {
            HttpStatus status = HttpStatus.NETWORK_AUTHENTICATION_REQUIRED;
            return handleException((NotAuthException) ex, headers, status, request);
        } else if (ex instanceof ParseException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return handleException((ParseException) ex, headers, status, request);
        } else if (ex instanceof ExistException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return handleException((ExistException) ex, headers, status, request);
        } else {
            logger.warn("Unknown exception type: " + ex.getClass().getName());
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, request);
        }
    }

    @ExceptionMetered
    protected ResponseEntity<Object> handleException(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        RestError.Builder builder = new RestError.Builder();
        builder.setCode(status.value());
        builder.setStatus(status.getReasonPhrase());
        builder.setMessage(ex.getMessage());
        builder.setThrowable(ex.getCause());
        return handleExceptionInternal(ex, builder.build(), headers, status, request);
    }

    public RestExceptionHandler() {
        super();
    }

    @ExceptionMetered
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        RestError.Builder builder = new RestError.Builder();
        String message = "Unsupported http method: " + ex.getMethod() + "; Supported http method: " + Arrays.toString(ex.getSupportedMethods());
        builder.setCode(status.value()).setMessage(message).setStatus(status.getReasonPhrase());
        return handleExceptionInternal(ex, builder.build(), headers, status, request);
    }
}
