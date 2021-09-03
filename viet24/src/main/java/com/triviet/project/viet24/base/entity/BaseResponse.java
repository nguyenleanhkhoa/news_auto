package com.triviet.project.viet24.base.entity;

import com.fasterxml.jackson.annotation.*;

public class BaseResponse<T> {
    private long statusCode;
    private String message;
    private T result;

	public BaseResponse(long statusCode, String message, T result) {
		this.statusCode = statusCode;
		this.message = message;
		this.result = result;
	}

	@JsonProperty("status_code")
    public long getStatusCode() {
        return statusCode;
    }

    @JsonProperty("status_code")
    public void setStatusCode(long value) {
        this.statusCode = value;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String value) {
        this.message = value;
    }

    @JsonProperty("result")
    public T getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(T value) {
        this.result = value;
    }
}
