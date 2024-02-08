package com.anik.app.response.error;

import com.anik.app.response.contract.ApiErrorResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.List;
import java.util.stream.Collectors;

final public class ErrorResponseUtils implements ApiErrorResponse {
    private static final String TRACE = "trace";
    @Value("${application.exception.trace:true}")
    private boolean printStackTrace;

    private ErrorResponseUtils() {
    }

    public static ResponseEntity<Object> buildErrorResponse(Exception ex,
                                                            HttpStatusCode statusCode,
                                                            WebRequest request) {
        return buildErrorResponseEntity(ex, statusCode.value(), ex.getMessage(), request);
    }

    public static ResponseEntity<Object> buildErrorResponse(Exception ex,
                                                            HttpStatusCode statusCode,
                                                            String message,
                                                            WebRequest request) {
        return buildErrorResponseEntity(ex, statusCode.value(), message, request);
    }

    public static ResponseEntity<Object> buildMethodArgumentNotValidErrorResponse(MethodArgumentNotValidException ex,
                                                                                  HttpStatusCode statusCode,
                                                                                  WebRequest request) {
        final String VALIDATION_ERROR_MSG = "Validation error. Check 'fieldErrors' field for details.";
        return buildErrorResponseEntity(ex, statusCode.value(), VALIDATION_ERROR_MSG, request);
    }

    private static ResponseEntity<Object> buildErrorResponseEntity(Exception ex,
                                                                   Integer statusCode,
                                                                   String message,
                                                                   WebRequest request) {
        ErrorResponse errorResponseBody = ErrorResponse.builder()
              .status(statusCode)
              .title(HttpStatus.valueOf(statusCode).getReasonPhrase())
              .message(message)
              .stackTrace(isTraceOn(request) ? ExceptionUtils.getStackTrace(ex) : null)
              .build();
        if (ex instanceof MethodArgumentNotValidException)
            setValidationErrors(errorResponseBody, ((MethodArgumentNotValidException) ex).getFieldErrors());
        return ResponseEntity.status(statusCode).body(errorResponseBody);
    }

    private static void setValidationErrors(ErrorResponse errorResponse, List<FieldError> fieldErrors) {
        errorResponse.setFieldErrors(fieldErrors.stream()
              .map(fieldError -> ValidationError.builder()
                    .field(fieldError.getField())
                    .reason(fieldError.getDefaultMessage())
                    .rejectedValue(fieldError.getRejectedValue())
                    .build())
              .collect(Collectors.toList()));
    }

    private static boolean isTraceOn(WebRequest request) {
        String[] value = request.getParameterValues(TRACE);
        return value != null && value.length > 0 && value[0].contentEquals("true");
    }
}
