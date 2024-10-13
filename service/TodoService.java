package service;

import java.util.ArrayList;

import entity.Todo;

public interface TodoService {

        ArrayList<Todo> serviceGetAllTodo();

        void serviceShowTodo();

        void serviceAddTodo(String todo);

        void serviceRemoveTodo(Integer id);

        Todo serviceGetTodoById(Integer id);

        ArrayList<Todo> serviceGetAllTodos();

        void serviceUpdateTodo(Integer id, String newTitle, boolean newStatus);

        void serviceSortingTodo(String nomor);

        void serviceSearchTodo(String huruf);
}