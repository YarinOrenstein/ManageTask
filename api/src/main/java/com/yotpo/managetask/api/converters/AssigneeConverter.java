package com.yotpo.managetask.api.converters;

import com.yotpo.managetask.core.entities.Assignee;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AssigneeConverter {

    public Assignee assigneeFromRequest(RequestEntity<String> request) throws JSONException {
        JSONObject jsonAssigneeObject = new JSONObject(request.getBody());
        return assigneeFromJSONObject(jsonAssigneeObject);
    }

    public Assignee assigneeFromJSONObject(JSONObject jsonObject) throws JSONException {
        return Assignee.builder()
                .assignee_id(jsonObject.getLong("assignee_id"))
                .first_name(jsonObject.getString("first_name"))
                .last_name(jsonObject.getString("last_name"))
                .build();
    }

    public String toAssigneeResponse(Assignee assignee) {
        try{
            return new JSONObject()
                    .put("assignee_id", assignee.getAssignee_id())
                    .put("first_name", assignee.getFirst_name())
                    .put("last_name", assignee.getLast_name())
                    .put("tasks", assignee.getTasks())
                    .toString();
        } catch (JSONException e){
            e.printStackTrace();
            return "Issue parsing employee JSON";
        }
    }

    public String toAssigneesResponse(List<Assignee> assignees) {
        return assignees.stream().map(this::toAssigneeResponse).collect(Collectors.toList()).toString();
    }
}
