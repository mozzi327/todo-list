package com.example.todolist.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "TODOS")
@Getter
@Setter
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, name = "order_num")
    private Long order;

    @Column(nullable = false)
    private boolean completed;

    public Todo(String title, Long order, boolean completed) {
        this.title = title;
        this.order = order;
        this.completed = completed;
    }
}
