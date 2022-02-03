package com.yotpo.managetask.core.services;

import com.yotpo.managetask.core.exceptions.AssigneeNotFoundException;
import com.yotpo.managetask.core.entities.Assignee;
import com.yotpo.managetask.core.repositories.AssigneeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssigneeService {
    @Autowired
    private AssigneeRepository assigneeRepository;

    public Assignee create(Assignee assignee) { return assigneeRepository.save(assignee); }

    public List<Assignee> getAll() { return (List<Assignee>) assigneeRepository.findAll(); }

    public Assignee get(Long id) { return assigneeRepository.findById(id)
            .orElseThrow(() -> new AssigneeNotFoundException(id)); }

    public void delete(Long id) {
        //Also need to check for children records before deleting.
        assigneeRepository.deleteById(id);
    }

    public Assignee getByFirstNameAndLastName(String firstName, String lastName){
        List<Assignee> assignees = getAll();
        synchronized (assignees){
            Iterator<Assignee> iterator = assignees.iterator();
            while(iterator.hasNext()){
                Assignee assignee = (Assignee) iterator.next();
                if (assignee.getFirst_name().equals(firstName)
                        && assignee.getLast_name().equals(lastName)) {
                    return assignee;
                }
            }
            return null;
        }
    }
}