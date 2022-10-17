package com.example.todolist.dto;

import com.example.todolist.entity.Todo;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {
    private Long id;
    private String title;
    private Long order;
    private Boolean completed;
    private String url;

    public TodoDto(Todo todo, String serviceUrl) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.order = todo.getOrder();
        this.completed = todo.isCompleted();
        this.url = serviceUrl + this.id;
    }
}
