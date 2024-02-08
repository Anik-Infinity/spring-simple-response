package com.anik.app.response.error;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@SuperBuilder
final public class ValidationError {
    private String field;
    private Object rejectedValue;
    private String reason;
}