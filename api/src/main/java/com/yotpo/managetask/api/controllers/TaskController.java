package com.yotpo.managetask.api.controllers;

import com.yotpo.managetask.api.converters.AssigneeConverter;
import com.yotpo.managetask.api.converters.TaskConverter;
import com.yotpo.managetask.core.entities.Assignee;
import com.yotpo.managetask.core.entities.Task;
import com.yotpo.managetask.core.services.AssigneeService;
import com.yotpo.managetask.core.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping()
    @RequestMapping("/getAll")
    public ResponseEntity<String> getAll() {
        List<Task> tasks = taskService.getAll();
        return ResponseEntity.status(HttpStatus.FOUND).body(taskConverter.toTasksResponse(tasks));
    }

    @GetMapping()
    @RequestMapping("/get/{id}")
    public ResponseEntity<String> getTask(@PathVariable Long id) {
        Task task = taskService.get(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(taskConverter.toTaskResponse(task));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        //Also need to check for children records before deleting.
        taskService.delete(id);
        return ResponseEntity.status(HttpStatus.FOUND).body("Deleted");
    }

    @PostMapping()
    @RequestMapping("/edit/{id}")
    public ResponseEntity<String> edit(@PathVariable Long id, RequestEntity<String> taskRequest) throws JSONException {
        Task editedTask = taskConverter.taskFromRequest(taskRequest);
        taskService.edit(id, editedTask.getAssignee(), editedTask.getDue_date(), editedTask.getTitle(), editedTask.getStatus());
        return ResponseEntity.status(HttpStatus.CREATED).body(taskConverter.toTaskResponse(editedTask));
    }
}
