package com.example.todolist.service;

import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public Todo addTodoList(Todo todo) {
        Todo addTodo = todoRepository.save(todo);
        return addTodo;
    }

    public Todo updateTodoList(Todo todo) {
        Todo findTodoList = findVerifiedTodoList(todo.getId());
        Optional.ofNullable(todo.getTitle())
                .ifPresent(title -> findTodoList.setTitle(title));
        Optional.ofNullable(todo.getOrder())
                .ifPresent(order -> findTodoList.setOrder(order));

        return todoRepository.save(findTodoList);
    }

    private Todo findVerifiedTodoList(Long id) {
        Optional<Todo> findTodo = todoRepository.findById(id);
        return findTodo.orElse(null);
    }

    public Todo findTodolist(Long id) {
        Optional<Todo> findTodo = todoRepository.findById(id);

        return findTodo.orElse(null);
    }

    public List<Todo> findAllTodolist() {
        List<Todo> findAlltodo = todoRepository.findAll();

        return findAlltodo;
    }

    public void deleteTodolist(Long id) {
        todoRepository.deleteById(id);
    }

    public void deleteAllTodolist() {
        todoRepository.deleteAll();
    }
}
