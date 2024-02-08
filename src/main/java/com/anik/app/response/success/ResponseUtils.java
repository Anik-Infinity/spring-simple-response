package com.anik.app.response.success;

import com.anik.app.dto.BaseDto;
import com.anik.app.response.contract.ApiResponse;
import com.anik.app.response.contract.ApiSuccessResponse;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

final public class ResponseUtils implements ApiSuccessResponse {
    private ResponseUtils() {
    }

    public static ResponseEntity<ApiResponse> buildResponse(String message) {
        return buildResponseEntity(HttpStatus.OK, null, message);
    }

    public static ResponseEntity<ApiResponse> buildResponse(HttpStatus httpStatus,
                                                            String message) {
        return buildResponseEntity(httpStatus, null, message);
    }

    public static <T extends BaseDto> ResponseEntity<ApiResponse> buildResponse(HttpStatus httpStatus,
                                                                                T data,
                                                                                String message) {
        return buildResponseEntity(httpStatus, data, message);
    }

    public static <T extends BaseDto> ResponseEntity<ApiResponse> buildPaginatedResponse(Page<T> page,
                                                                                         String message) {
        return buildPaginatedResponseEntity(page, message);
    }

    private static <T extends BaseDto> ResponseEntity<ApiResponse> buildResponseEntity(HttpStatus httpStatus,
                                                                                       @Nullable T data,
                                                                                       String message) {
        return ResponseEntity.status(httpStatus.value())
              .body(SuccessResponse.<T>builder()
                    .status(httpStatus.value())
                    .title(httpStatus.getReasonPhrase())
                    .message(message)
                    .data(data)
                    .build());

    }

    private static <T extends BaseDto> ResponseEntity<ApiResponse> buildPaginatedResponseEntity(Page<T> page,
                                                                                                String message) {
        return ResponseEntity.status(HttpStatus.OK.value())
              .body(PaginatedResponse.<T>builder()
                    .status(HttpStatus.OK.value())
                    .title(HttpStatus.OK.getReasonPhrase())
                    .message(message)
                    .page(page.getPageable().getPageNumber())
                    .size(page.getSize())
                    .totalPages(page.getTotalPages())
                    .totalRecords(page.getTotalElements())
                    .data(page.getContent())
                    .build());

    }
}
