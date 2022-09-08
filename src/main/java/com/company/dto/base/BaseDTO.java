package com.company.dto.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseDTO {
    @JsonProperty("ID")
    private String   id;
}
