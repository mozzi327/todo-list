package com.example.todolist.controller;

import com.example.todolist.dto.TodoDto;
import com.example.todolist.entity.Todo;
import com.example.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @Value("${server.service.url}")
    private String serviceUrl;

    @PostMapping
    public ResponseEntity addTodo(@RequestBody TodoDto todo) {
        if (ObjectUtils.isEmpty(todo.getTitle())) {
            return ResponseEntity.badRequest().build();
        }

        if (ObjectUtils.isEmpty(todo.getOrder())) {
            todo.setOrder(0L);
        }

        if (ObjectUtils.isEmpty(todo.getCompleted())) {
            todo.setCompleted(false);
        }

        Todo addTodo = todoService.addTodoList(todo);
        TodoDto response = new TodoDto(addTodo, serviceUrl);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("{todo-id}")
    public ResponseEntity updateTodo(@PathVariable("todo-id") Long id,
                                     @RequestBody TodoDto edit) {
        edit.setId(id);
        Todo editTodo = todoService.updateTodoList(id, edit);
        TodoDto response = new TodoDto(editTodo, serviceUrl);

        return ResponseEntity.ok(response);
    }

    @GetMapping("{todo-id}")
    public ResponseEntity findTodo(@PathVariable("todo-id") Long id) {
        Todo findTodo = todoService.findTodolist(id);
        TodoDto response = new TodoDto(findTodo, serviceUrl);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity findAllTodo() {
        List<Todo> findAll = todoService.findAllTodolist();

        List<TodoDto> responses = findAll.stream()
                .map(todo -> new TodoDto(todo, serviceUrl))
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("{todo-id}")
    public ResponseEntity deleteTodo(@PathVariable("todo-id") Long id) {
        todoService.deleteTodolist(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity deleteAllTodo() {
        todoService.deleteAllTodolist();
        return ResponseEntity.ok().build();
    }
}
