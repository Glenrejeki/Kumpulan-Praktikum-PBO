package service;

import java.util.ArrayList;
import java.util.List;

import entity.Todo;
import repository.TodoRepository;
import repository.TodoRepositoryImpl;

public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private TodoRepositoryImpl todoRepositoryImpl;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    static class Data {
        private String name;
        
        public Data(String name) {
            this.name = name;
        }
        
        public String getName() {
            return this.name;
        }
    }
      
    public ArrayList<Todo> data = new ArrayList<Todo>();

    @Override
    public ArrayList<Todo> serviceGetAllTodo() {
        return data;
    }

    @Override
    public void serviceShowTodo() {
        ArrayList<Todo> todos = todoRepository.repoGetAllTodo();
        System.out.println("Daftar Todo:");
        
        if (todos.size() <= 0) {
            System.out.println("- Data todo belum tersedia!");
            return;
        }
        
        for (Todo todo : todos) {
            if (todo != null) {
                System.out.println(todo);
            } 
            else {
                break;
            }
        }
    }

    @Override
    public void serviceAddTodo(String todo) {
        Todo newTodo = new Todo(todo);
        todoRepository.repoAddTodo(newTodo);
    }
    
    @Override
    public void serviceRemoveTodo(Integer id) {
        boolean success = todoRepository.repoRemoveTodo(id);
        
        if (!success) {
            System.out.printf("[!] Gagal menghapus todo dengan ID: %d.\n", id);
            return;
        }
    }

    @Override
    public Todo serviceGetTodoById(Integer id) {
        ArrayList<Todo> todos = todoRepository.repoGetAllTodo(); 
        
        for (Todo todo : todos) {
            if (todo != null && todo.getId().equals(id)) { 
                return todo;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Todo> serviceGetAllTodos() {
        return todoRepository.repoGetAllTodo();
    }

    @Override
    public void serviceUpdateTodo(Integer id, String newTitle, boolean newStatus) {
        Todo todoToUpdate = serviceGetTodoById(id);
        if (todoToUpdate == null) {
            System.out.printf("[!] Todo dengan ID %d tidak ditemukan.\n", id);
            return;
        }

        Todo updatedTodo = new Todo();
        updatedTodo.setTitle(newTitle);
        updatedTodo.setFinished(newStatus);

        boolean success = todoRepository.repoUpdateTodo(id, updatedTodo);
    }

    @Override
    public void serviceSortingTodo(String nomor) {
        int newhuruf = Integer.parseInt(nomor);
        List<Todo> todos = todoRepository.repoGetAllTodo();

        if (todos == null){
            System.out.println("[!] Tindakan mengurutkan todo dibatalkan.");
        }
        else if (newhuruf < 1 || newhuruf > 4) {
            System.out.println("[!] Tindakan mengurutkan todo dibatalkan.");
        }

        switch (nomor) {
            case "1":
                todoRepositoryImpl.SortData2(todos, true); 
                break;
            case "2":
                todoRepositoryImpl.SortData2(todos, false); 
                break;
            case "3":
                todoRepositoryImpl.SortData1(todos, true); 
                break;
            case "4":
                todoRepositoryImpl.SortData1(todos, false); 
                break;
            default:
                break;
        }
    }

    @Override
    public void serviceSearchTodo(String huruf){
        List<Todo> todos = todoRepository.repoGetAllTodo();

        todoRepositoryImpl.SearchData(todos, huruf);
    
    }
}