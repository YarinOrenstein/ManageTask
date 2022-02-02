package com.yotpo.managetask.api.controllers;

import com.yotpo.managetask.api.converters.TaskConverter;
import com.yotpo.managetask.api.generated.model.TaskRequest;
import com.yotpo.managetask.api.generated.model.TaskResponse;
import com.yotpo.managetask.api.generated.model.TasksResponse;
import com.yotpo.managetask.core.entities.Task;
import com.yotpo.managetask.core.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.yotpo.managetask.api.generated.TasksApi;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class TaskController implements TasksApi{
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskConverter taskConverter;

    @Override
    public ResponseEntity<TaskResponse> create(TaskRequest taskRequest) {
        Task createdTask = taskService.create(taskConverter.taskFromRequest(taskRequest));
        TaskResponse taskResponse = taskConverter.toTaskResponse(createdTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskResponse);
    }

    @Override
    public ResponseEntity<TasksResponse> index() {
        List<Task> tasks = taskService.getAll();
        return ResponseEntity.status(HttpStatus.FOUND).body(taskConverter.toTasksResponse(tasks));
    }

    @Override
    public ResponseEntity<TaskResponse> findById(Long id) {
        Task task = taskService.get(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(taskConverter.toTaskResponse(task));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        //Also need to check for children records before deleting.
        taskService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<TaskResponse> update(Long id, TaskRequest taskRequest) {
        Task task = taskConverter.taskFromRequest(taskRequest);
        task.setTask_id(id);
        Task updatedTask = taskService.update(task);
        TaskResponse taskResponse = taskConverter.toTaskResponse(updatedTask);
        return ResponseEntity.status(HttpStatus.FOUND).body(taskResponse);
    }
}
