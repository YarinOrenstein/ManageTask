package com.yotpo.managetask.core.exceptions;

public class AssigneeNotFoundException extends RuntimeException{

    public AssigneeNotFoundException(Long id) {
        super("Couldn't find assignee " +  id);
    }
}