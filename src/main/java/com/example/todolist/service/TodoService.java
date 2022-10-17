package com.example.todolist.service;

import com.example.todolist.dto.TodoDto;
import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public Todo addTodoList(TodoDto todoDto) {
        Todo todo = Todo.builder()
                .title(todoDto.getTitle())
                .order(todoDto.getOrder())
                .completed(todoDto.getCompleted())
                .build();

        return todoRepository.save(todo);
    }

    public Todo updateTodoList(Long id, TodoDto todoDto) {

        Todo findTodoList = findTodolist(id);

        if (todoDto.getTitle() != null) {
            findTodoList.setTitle(todoDto.getTitle());
        }

        if (todoDto.getOrder() != null) {
            findTodoList.setOrder(todoDto.getOrder());
        }

        if (todoDto.getCompleted() != null) {
            findTodoList.setCompleted(todoDto.getCompleted());
        }

        return todoRepository.save(findTodoList);
    }

    public Todo findTodolist(Long id) {
        Optional<Todo> findTodo = todoRepository.findById(id);

        return findTodo.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Todo> findAllTodolist() {
        return todoRepository.findAll();
    }

    public void deleteTodolist(Long id) {
        todoRepository.deleteById(id);
    }

    public void deleteAllTodolist() {
        todoRepository.deleteAll();
    }
}
