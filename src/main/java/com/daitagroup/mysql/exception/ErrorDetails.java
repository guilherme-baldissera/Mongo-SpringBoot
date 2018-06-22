package com.daitagroup.mysql.exception;

import lombok.Getter;

import java.util.Date;

public class ErrorDetails {

    @Getter
    private Date timestamp;
    @Getter
    private String message;
    @Getter
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }


}
