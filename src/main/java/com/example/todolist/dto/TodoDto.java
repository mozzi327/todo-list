package com.example.todolist.dto;

import com.example.todolist.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class TodoDto {

    @Getter
    @AllArgsConstructor
    public static class Post {
        private String title;
        private Long order;
        private boolean completed;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch {
        private Long id;
        private String title;
        private Long order;
        private boolean completed;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String title;
        private Long order;
        private boolean completed;
    }
}
