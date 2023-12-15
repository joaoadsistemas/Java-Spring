package com.joaosilveira.challengereventcity.controllers.handlers;

import com.joaosilveira.challengereventcity.dto.CustomErr;
import com.joaosilveira.challengereventcity.service.exceptions.DataBaseException;
import com.joaosilveira.challengereventcity.service.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErr> resourceNotFoundException(ResourceNotFoundException e,
                                                               HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomErr err = new CustomErr(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<CustomErr> databaseException(DataBaseException e,
                                                               HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomErr err = new CustomErr(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
