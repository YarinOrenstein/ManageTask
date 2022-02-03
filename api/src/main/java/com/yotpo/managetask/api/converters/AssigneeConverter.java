package com.yotpo.managetask.api.converters;

import com.yotpo.managetask.api.generated.model.*;
import com.yotpo.managetask.core.entities.Assignee;
import com.yotpo.managetask.core.entities.Task;
import com.yotpo.managetask.core.services.AssigneeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AssigneeConverter {
    @Autowired
    private AssigneeService assigneeService;

    public Assignee assigneeFromRequest(AssigneeRequest assigneeRequest) {
        AssigneeRequestAssignee assignee = assigneeRequest.getAssignee();
        Assignee existingAssignee = assigneeService.getByFirstNameAndLastName(assignee.getFirstName(),assignee.getLastName());
        if (existingAssignee != null){
            return existingAssignee;
        }
        else{
            Assignee newAssignee = Assignee.builder()
                    .first_name(assignee.getFirstName())
                    .last_name(assignee.getLastName())
                    .build();
            return assigneeService.create(newAssignee);
        }
    }

    public AssigneeResponse toAssigneeResponse(Assignee assignee) {
        AssigneeResponseAssignee assigneeResponseAssignee = new AssigneeResponseAssignee();
        assigneeResponseAssignee.assigneeId(assignee.getAssignee_id());
        assigneeResponseAssignee.setFirstName(assignee.getFirst_name());
        assigneeResponseAssignee.setLastName(assignee.getLast_name());
        AssigneeResponse assigneeResponse = new AssigneeResponse();
        assigneeResponse.setAssignee(assigneeResponseAssignee);

        return assigneeResponse;
    }

    public AssigneesResponse toAssigneesResponse(List<Assignee> assignees){
        List<AssigneeResponse> assigneeResponses = assignees.stream().map(this::toAssigneeResponse).collect(Collectors.toList());
        AssigneesResponse response = new AssigneesResponse();
        response.setAssignees(assigneeResponses);

        return response;
    }
}