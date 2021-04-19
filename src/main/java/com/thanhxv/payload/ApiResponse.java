package com.thanhxv.payload;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@JsonPropertyOrder({
        "success",
        "message"
})
public class ApiResponse implements Serializable {

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("message")
    private String message;

    @JsonIgnore
    private HttpStatus status;

    public ApiResponse() {

    }

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApiResponse(Boolean success, String message, HttpStatus httpStatus) {
        this.success = success;
        this.message = message;
        this.status = httpStatus;
    }
}
