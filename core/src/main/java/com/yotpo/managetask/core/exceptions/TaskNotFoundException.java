package com.yotpo.managetask.core.exceptions;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Couldn't find task " +  id);
    }
}
