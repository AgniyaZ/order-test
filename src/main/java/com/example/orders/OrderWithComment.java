package com.example.orders;

import java.util.List;

public class OrderWithComment {
    private Integer id;
    private String description;
    private Status status;
    private List<Comment> comments;

    public OrderWithComment(Order order, List<Comment> comments) {
        this.id = order.getId();
        this.description = order.getDescription();
        this.status = order.getStatus();
        this.comments = comments;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
