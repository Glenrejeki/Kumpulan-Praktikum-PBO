package repository;

import java.util.ArrayList;
import entity.Todo;

public interface TodoRepository {
    ArrayList<Todo> repoGetAllTodo();
  
    void repoAddTodo(Todo newTodo);
    
    boolean repoRemoveTodo(Integer idTodo);

    boolean repoUpdateTodo(Integer nomor , Todo baru );
}