package net.parwand.employeemanagerangular.exception;

import org.springframework.data.crossstore.ChangeSetPersister;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
