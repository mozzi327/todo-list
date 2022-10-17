package com.example.todolist.mapper;

import com.example.todolist.dto.TodoDto;
import com.example.todolist.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TodoMapper {
    Todo todoPostDtoToTodo(TodoDto.Post requestBody);
    Todo todoPatchDtoToTodo(TodoDto.Patch requestBody);
    TodoDto.Response todoToResponse(Todo todo);
}
