package com.dericorp.notification.dto;

public record ErrorResponse(
        String timeStamp,
        int status,
        String message,
        String error,
        String path

) {
}
