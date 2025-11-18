package com.app.features.course.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(String message) {
        super(message);
    }

    public CourseNotFoundException(Long id) {
        super(String.format("Course with ID %d not found", id));
    }

    public CourseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
