package com.yotpo.managetask.api.converters;

import com.yotpo.managetask.core.entities.Assignee;
import com.yotpo.managetask.core.entities.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskConverter {

    @Autowired
    private AssigneeConverter assigneeConverter;

    public Task taskFromRequest(RequestEntity<String> request) throws JSONException {
        JSONObject jsonTaskObject = new JSONObject(request.getBody());
        return taskFromJSONObject(jsonTaskObject);
    }

    public Task taskFromJSONObject(JSONObject jsonObject) throws JSONException {
        return Task.builder()
                .title(jsonObject.getString("title"))
                .status(jsonObject.getString("status"))
                .assignee(assigneeConverter.assigneeFromJSONObject(jsonObject.getJSONObject("assignee")))
                .due_date(LocalDate.parse(jsonObject.getString("due_date")))
                .build();
    }

    public List<Task> tasksFromRequest(RequestEntity<String> request) throws JSONException {
        JSONArray objs = new JSONArray(request.getBody());
        List<Task> tasks= new ArrayList<>();
        for (int i = 0; i < objs.length(); i++){
            tasks.add(taskFromJSONObject(objs.getJSONObject(i)));
        }

        return tasks;
    }

    public String toTaskResponse(Task task){
        try{
            return new JSONObject()
                    .put("task_id", task.getTask_id())
                    .put("status", task.getStatus())
                    .put("assignee", assigneeConverter.JSONObjectFromAssignee(task.getAssignee()))
                    .put("due_date", task.getDue_date())
                    .toString();

        } catch (JSONException e) {
            e.printStackTrace();
            return "Failed to create JSON from Task";
        }
    }

    public String toTasksResponse(List<Task> tasks){
        return tasks.stream().map(this::toTaskResponse).collect(Collectors.toList()).toString();
    }
}