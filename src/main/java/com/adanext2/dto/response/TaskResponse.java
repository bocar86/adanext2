package com.adanext2.dto.response;

import com.adanext2.model.Task;

public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private String status;
    private Long assigneeId;
    private String assigneeName;

    public static TaskResponse from(Task task) {
        TaskResponse response = new TaskResponse();
        response.id = task.getId();
        response.title = task.getTitle();
        response.description = task.getDescription();
        response.status = task.getStatus().name();
        if (task.getAssignee() != null) {
            response.assigneeId = task.getAssignee().getId();
            response.assigneeName = task.getAssignee().getName();
        }
        return response;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public Long getAssigneeId() { return assigneeId; }
    public String getAssigneeName() { return assigneeName; }
}