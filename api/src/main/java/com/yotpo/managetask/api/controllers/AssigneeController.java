package com.yotpo.managetask.api.controllers;

import com.yotpo.managetask.api.converters.AssigneeConverter;
import com.yotpo.managetask.api.converters.TaskConverter;
import com.yotpo.managetask.api.generated.AssigneesApi;
import com.yotpo.managetask.api.generated.model.AssigneeRequest;
import com.yotpo.managetask.api.generated.model.AssigneeResponse;
import com.yotpo.managetask.api.generated.model.AssigneesResponse;
import com.yotpo.managetask.core.entities.Assignee;
import com.yotpo.managetask.core.entities.Task;
import com.yotpo.managetask.core.services.AssigneeService;
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
public class AssigneeController implements AssigneesApi {
    @Autowired
    private AssigneeService assigneeService;
    @Autowired
    private AssigneeConverter assigneeConverter;
    @Autowired
    private TaskConverter taskConverter;

    @Override
    public ResponseEntity<AssigneeResponse> create(AssigneeRequest assigneeRequest) {
        Assignee createdAssignee = assigneeService.create(assigneeConverter.assigneeFromRequest(assigneeRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(assigneeConverter.toAssigneeResponse(createdAssignee));
    }

    @Override
    public ResponseEntity<AssigneesResponse> index() {
        List<Assignee> assignees = assigneeService.getAll();
        return ResponseEntity.status(HttpStatus.FOUND).body(assigneeConverter.toAssigneesResponse(assignees));
    }

    @Override
    public ResponseEntity<AssigneeResponse> findById(Long id) {
        Assignee assignee = assigneeService.get(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(assigneeConverter.toAssigneeResponse(assignee));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        //Also need to check for children records before deleting.
        assigneeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //TODO: view all existing tasks per assignee, sortable of filtered by assignee, due date, and status
//    @Override
//    public ResponseEntity<String> getAssigneeTasks(@PathVariable Long id) {
//        Assignee assignee = assigneeService.get(id);
//        List<Task> tasks = assignee.getTasks();
//        return ResponseEntity.status(HttpStatus.FOUND).body(taskConverter.toTasksResponse(tasks));
//    }
}