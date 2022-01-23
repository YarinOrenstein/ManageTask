package com.yotpo.managetask.core.entities;

import lombok.experimental.SuperBuilder;
import javax.persistence.*;
import java.util.Date;

@Entity(name = "tasks")
@SuperBuilder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long task_id;

    private String title;
    private String status;
    private Date due_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private Assignee assignee;

    public Task() {
    }

    @Override
    public String toString() {
        return "Task{" +
                "task_id=" + task_id +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                ", due_date=" + due_date +
                ", assignee=" + assignee +
                '}';
    }

    public Long getTask_id() {
        return task_id;
    }

    public void setTask_id(Long id) {
        this.task_id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Assignee getAssignee() {
        return assignee;
    }

    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date dueDate) {
        this.due_date = dueDate;
    }
}