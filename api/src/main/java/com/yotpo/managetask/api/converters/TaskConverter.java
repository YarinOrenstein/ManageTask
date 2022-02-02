package com.yotpo.managetask.api.converters;

import com.yotpo.managetask.api.generated.model.*;
import com.yotpo.managetask.core.entities.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskConverter {

    @Autowired
    private AssigneeConverter assigneeConverter;

    public Task taskFromRequest(TaskRequest taskRequest) {
        TaskRequestTask task = taskRequest.getTask();

        return Task.builder()
                .title(task.getTitle())
                .status(task.getStatus())
                .due_date(task.getDueDate())
                .assignee(assigneeConverter.assigneeFromRequest(task.getAssignee()))
                .build();
    }

    public TaskResponse toTaskResponse(Task task){
        TaskResponseTask taskResponseTask = new TaskResponseTask();
        taskResponseTask.setTaskId(task.getTask_id());
        taskResponseTask.setTitle(task.getTitle());
        taskResponseTask.setAssignee(assigneeConverter.toAssigneeResponse(task.getAssignee()));
        taskResponseTask.setDueDate(task.getDue_date());
        taskResponseTask.setStatus(task.getStatus());
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setTask(taskResponseTask);

        return taskResponse;
    }

    public TasksResponse toTasksResponse(List<Task> tasks){
        List<TaskResponse> taskResponses = tasks.stream().map(this::toTaskResponse).collect(Collectors.toList());
        TasksResponse response = new TasksResponse();
        response.setTasks(taskResponses);

        return response;
    }

    //    public List<Task> tasksFromRequest(RequestEntity<String> request) throws JSONException {
//        JSONArray objs = new JSONArray(request.getBody());
//        List<Task> tasks= new ArrayList<>();
//        for (int i = 0; i < objs.length(); i++){
//            tasks.add(taskFromJSONObject(objs.getJSONObject(i)));
//        }
//
//        return tasks;
//    }
}