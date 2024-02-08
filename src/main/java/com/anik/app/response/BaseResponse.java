package com.anik.app.response;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@SuperBuilder(toBuilder = true)
public abstract class BaseResponse {
    @Builder.Default
    private Instant timestamp = Instant.now();
    private int status;
    private String title;
    private String message;
}
