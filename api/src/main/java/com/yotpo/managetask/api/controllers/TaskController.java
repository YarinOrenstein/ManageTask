package com.yotpo.managetask.api.controllers;

import com.yotpo.managetask.api.converters.AssigneeConverter;
import com.yotpo.managetask.api.converters.TaskConverter;
import com.yotpo.managetask.core.entities.Assignee;
import com.yotpo.managetask.core.entities.Task;
import com.yotpo.managetask.core.services.AssigneeService;
import com.yotpo.managetask.core.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskConverter taskConverter;

    @PostMapping()
    @RequestMapping("/add")
    public ResponseEntity<String> create(RequestEntity<String> taskRequest) throws JSONException {
        Task newTask = taskService.create(taskConverter.taskFromRequest(taskRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(taskConverter.toTaskResponse(newTask));
    }
}
