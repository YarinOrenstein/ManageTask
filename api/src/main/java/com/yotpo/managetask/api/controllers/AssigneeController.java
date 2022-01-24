package com.yotpo.managetask.api.controllers;

import com.yotpo.managetask.api.converters.AssigneeConverter;
import com.yotpo.managetask.core.entities.Assignee;
import com.yotpo.managetask.core.services.AssigneeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/assignees")
public class AssigneeController {
    @Autowired
    private AssigneeService assigneeService;
    @Autowired
    private AssigneeConverter assigneeConverter;

    @PostMapping()
    @RequestMapping("/add")
    public ResponseEntity<String> create(RequestEntity<String> assigneeRequest) throws JSONException {
        Assignee newAssingee = assigneeService.create(assigneeConverter.assigneeFromRequest(assigneeRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(assigneeConverter.toAssigneeResponse(newAssingee));
    }

    @GetMapping()
    @RequestMapping("/getAll")
    public ResponseEntity<String> getAll() {
        List<Assignee> assignees = assigneeService.getAll();
        return ResponseEntity.status(HttpStatus.FOUND).body(assigneeConverter.toAssigneesResponse(assignees));
    }

    @GetMapping()
    @RequestMapping("/get/{id}")
    public ResponseEntity<String> getAssignee(@PathVariable Long id) {
        Assignee assignee = assigneeService.get(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(assigneeConverter.toAssigneeResponse(assignee));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        //Also need to check for children records before deleting.
        assigneeService.delete(id);
        return ResponseEntity.status(HttpStatus.FOUND).body("Deleted");
    }
}