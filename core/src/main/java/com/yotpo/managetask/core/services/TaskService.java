package com.yotpo.managetask.core.services;

import com.yotpo.managetask.core.entities.Assignee;
import com.yotpo.managetask.core.entities.Task;
import com.yotpo.managetask.core.exceptions.TaskNotFoundException;
import com.yotpo.managetask.core.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public void edit(Long id, Assignee newAssignee, LocalDate newDue_date, String newTitle, String newStatus) {
        Task taskToEdit = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        taskToEdit.setAssignee(newAssignee);
        taskToEdit.setDue_date(newDue_date);
        taskToEdit.setTitle(newTitle);
        taskToEdit.setStatus(newStatus);
        taskRepository.save(taskToEdit);
    }
}
