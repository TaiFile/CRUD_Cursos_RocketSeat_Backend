package com.app.features.course.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class CourseNotExistsException extends RuntimeException {
    public CourseNotExistsException(String message) {
        super(message);
    }
}
