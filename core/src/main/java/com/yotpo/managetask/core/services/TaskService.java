package com.yotpo.managetask.core.services;

import com.yotpo.managetask.core.entities.Assignee;
import com.yotpo.managetask.core.entities.Task;
import com.yotpo.managetask.core.exceptions.AssigneeNotFoundException;
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

//    public Task get(Long id) { return taskRepository.findById(id)
//            .orElseThrow(() -> new AssigneeNotFoundException(id)); }
//
//    public void delete(Long id) {
//        //Also need to check for children records before deleting.
//        assigneeRepository.deleteById(id);
//    }
}
