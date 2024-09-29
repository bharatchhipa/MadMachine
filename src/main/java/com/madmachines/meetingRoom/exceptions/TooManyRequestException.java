package com.madmachines.meetingRoom.exceptions;

public class TooManyRequestException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TooManyRequestException(String message) {
        super(message);
    }
}
