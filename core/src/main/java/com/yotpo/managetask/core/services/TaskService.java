package com.yotpo.managetask.core.services;

import com.yotpo.managetask.core.entities.Task;
import com.yotpo.managetask.core.exceptions.TaskNotFoundException;
import com.yotpo.managetask.core.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task create(Task task) { return taskRepository.save(task); }

    public List<Task> getAll() { return (List<Task>) taskRepository.findAll(); }

    public Task get(Long id) { return taskRepository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException(id)); }

    public void delete(Long id) {
        //Also need to check for children records before deleting.
        taskRepository.deleteById(id);
    }

    public Task update(Task updatedTask) {
        Long id = updatedTask.getTask_id();
        Task taskToEdit = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        taskToEdit.setAssignee(updatedTask.getAssignee());
        taskToEdit.setDue_date(updatedTask.getDue_date());
        taskToEdit.setTitle(updatedTask.getTitle());
        taskToEdit.setStatus(updatedTask.getStatus());
        taskRepository.save(taskToEdit);
        return taskToEdit;
    }
}
