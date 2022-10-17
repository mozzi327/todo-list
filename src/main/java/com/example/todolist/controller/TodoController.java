package com.example.todolist.controller;

import com.example.todolist.dto.MultiResponseDto;
import com.example.todolist.dto.SingleResponseDto;
import com.example.todolist.dto.TodoDto;
import com.example.todolist.entity.Todo;
import com.example.todolist.mapper.TodoMapper;
import com.example.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;
    private final TodoMapper mapper;

    @PostMapping
    public ResponseEntity addTodo(@RequestBody TodoDto.Post todo) {
        Todo addTodo = todoService.addTodoList(mapper.todoPostDtoToTodo(todo));
        TodoDto.Response response = mapper.todoToResponse(addTodo);
        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.CREATED
        );
    }

    @PatchMapping("/{todo-id}")
    public ResponseEntity updateTodo(@PathVariable("todo-id") Long id,
                                     @RequestBody TodoDto.Patch edit) {
        edit.setId(id);
        Todo editTodo = todoService.updateTodoList(mapper.todoPatchDtoToTodo(edit));
        TodoDto.Response response = mapper.todoToResponse(editTodo);

        return new ResponseEntity(
                new SingleResponseDto<>(response), HttpStatus.OK
        );
    }

    @GetMapping("/{todo-id}")
    public ResponseEntity findTodo(@PathVariable("todo-id") Long id) {
        Todo findTodo = todoService.findTodolist(id);
        TodoDto.Response response = mapper.todoToResponse(findTodo);

        return new ResponseEntity(
                new SingleResponseDto<>(response), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity findAllTodo() {
        List<Todo> findAll = todoService.findAllTodolist();

        List<TodoDto.Response> responses = findAll.stream()
                .map(mapper::todoToResponse)
                .collect(Collectors.toList());

        return new ResponseEntity(
                new MultiResponseDto<>(responses), HttpStatus.OK
        );
    }

    @DeleteMapping("/{todo-id}")
    public ResponseEntity deleteTodo(@PathVariable("todo-id") Long id) {
        todoService.deleteTodolist(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity deleteAllTodo() {
        todoService.deleteAllTodolist();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
