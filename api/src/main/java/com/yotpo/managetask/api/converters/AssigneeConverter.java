package com.yotpo.managetask.api.converters;

import com.yotpo.managetask.core.entities.Assignee;
import com.yotpo.managetask.core.services.AssigneeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AssigneeConverter {
    @Autowired
    private AssigneeService assigneeService;

    public Assignee assigneeFromRequest(RequestEntity<String> request) throws JSONException {
        JSONObject jsonAssigneeObject = new JSONObject(request.getBody());
        return assigneeFromJSONObject(jsonAssigneeObject);
    }

    public Assignee assigneeFromJSONObject(JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("assignee_id")) {
            return assigneeService.get(jsonObject.getLong("assignee_id"));
        }
        else {
            Assignee assignee = Assignee.builder()
                    .first_name(jsonObject.getString("first_name"))
                    .last_name(jsonObject.getString("last_name"))
                    .build();
            assigneeService.create(assignee);
            return assignee;
        }
    }

    public String toAssigneeResponse(Assignee assignee) {
        try{
            return new JSONObject()
                    .put("assignee_id", assignee.getAssignee_id())
                    .put("first_name", assignee.getFirst_name())
                    .put("last_name", assignee.getLast_name())
//                    .put("tasks", assignee.getTasks())
                    .toString();
        } catch (JSONException e){
            e.printStackTrace();
            return "Issue parsing assignee JSON";
        }
    }

    public String toAssigneesResponse(List<Assignee> assignees) {
        return assignees.stream().map(this::toAssigneeResponse).collect(Collectors.toList()).toString();
    }

    public JSONObject JSONObjectFromAssignee(Assignee assignee) {
        try{
            return new JSONObject()
                    .put("assignee_id", assignee.getAssignee_id())
                    .put("first_name", assignee.getFirst_name())
                    .put("last_name", assignee.getLast_name());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}