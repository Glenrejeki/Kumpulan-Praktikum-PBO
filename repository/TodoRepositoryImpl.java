package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

import entity.Todo;

public class TodoRepositoryImpl implements TodoRepository {

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
    public ArrayList<Todo> repoGetAllTodo() {
        return data;
    }

    @Override
    public void repoAddTodo(Todo newTodo) {
        data.add(newTodo);
    }

    @Override
    public boolean repoRemoveTodo(Integer idTodo) {
        Todo targetTodo = data
        .stream()
        .filter(obj -> obj.getId() == idTodo)
        .findFirst()
        .orElse(null);
        
        if (targetTodo == null) {
        return false;
        }
        data.remove(targetTodo);
        return true;
    }

    @Override
    public boolean repoUpdateTodo(Integer nomor, Todo baru) {
        if (nomor <= 0 || nomor > data.size()) {  // Mengganti data.length dengan data.size()
            System.out.println("[!] Tidak ada To-Do pada posisi tersebut.");
            return false;
        }
    
        Todo todoLama = data.get(nomor - 1);
        baru.setId(todoLama.getId());
    
        data.set(nomor - 1, baru);  // Mengganti data[nomor - 1] dengan data.set()
    
        return true;
    }

    public static void  SortData1(List<Todo> data, boolean ascending){                             
        Comparator<Todo> comparator = Comparator.comparing(Todo::getTitle);
        if (!ascending) {
        comparator = comparator.reversed();
        }
        Collections.sort(data, comparator);
    }

    public static void  SortData2(List<Todo> data, boolean ascending){                             
        Comparator<Todo> comparator = Comparator.comparing(Todo::getId);
        if (!ascending) {
        comparator = comparator.reversed();
        }
        Collections.sort(data, comparator);
    }

    public static void SearchData(List<Todo> data, String keywords) {
        List<Todo> listbaru = new ArrayList<Todo>();

        String lowerCaseKeywords = keywords.toLowerCase();

        System.out.println("Hasil pencarian: " + keywords);
        int position = 0;
        int jumlah = 0;
        for (Todo string : data) {
            if (string.getTitle().toLowerCase().contains(lowerCaseKeywords)) {
                position++;
                jumlah += 1;
                listbaru.add(string);
            }
        }
        if (position > 0) {
            System.out.printf("Ditemukan %d Todo:", jumlah);
            System.out.println();
            for (int i = 0; i < listbaru.size(); i++) {
                Todo todo = listbaru.get(i);
                System.out.printf("%d | %s | %s%n", todo.getId(), todo.getTitle(), todo.isFinished() ? "Selesai" : "Belum Selesai");
            }
        }
}
}