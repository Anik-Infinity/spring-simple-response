package com.anik.app.response.success;

import com.anik.app.response.BaseResponse;
import com.anik.app.response.contract.ApiSuccessResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
final public class SuccessResponse<T> extends BaseResponse implements ApiSuccessResponse {
    private T data;
}
