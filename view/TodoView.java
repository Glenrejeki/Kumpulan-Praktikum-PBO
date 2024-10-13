package view;

import java.util.ArrayList;

import entity.Todo;
import service.TodoService;
import util.InputUtil;


public class TodoView {

    private TodoService todoService;

    public TodoView(TodoService todoService) {

        this.todoService = todoService;

    }

    /**
     * Menampilkan view todo
     */

    public void viewShowTodo() {
        while (true) {

            todoService.serviceShowTodo();

            System.out.println("Menu:");
            System.out.println("1. Tambah");
            System.out.println("2. Ubah");
            System.out.println("3. Hapus");
            System.out.println("4. Urutan");
            System.out.println("5. Cari");
            System.out.println("x. Keluar");
            var input = InputUtil.input("Pilih");
            var stop = false;

            switch (input) {
                case "1" -> viewAddTodo();
                case "2" -> viewUpdateTodo();
                case "3" -> viewRemoveTodo();
                case "4" -> viewSortingTodo();
                case "5" -> viewSearchTodo();
                case "x" -> stop = true;
                default -> System.out.println("[!] Pilihan tidak dimengerti.");

            }

            if (stop) {

                break;

            }

            System.out.println();

        }

    }

    public void viewAddTodo() {
        System.out.println("[Menambah Todo]");

        var title = InputUtil.input("Judul (x Jika Batal)");

        if (!title.equals("x")) {

            ArrayList<Todo> todos = todoService.serviceGetAllTodos(); 

            for (Todo todo : todos) {
                if (todo != null && todo.getTitle().equalsIgnoreCase(title)) {
                    System.out.printf("[!] Todo: %s, telah tersedia.\n", title);
                    return; 
                }
            }

            todoService.serviceAddTodo(title);
        
        }
    }

     public void viewRemoveTodo() {
        System.out.println("[Menghapus Todo]");
    
        var strIdTodo = InputUtil.input("[ID Todo] yang dihapus (x Jika Batal)");
    
        if (!strIdTodo.equals("x")) {
            int idTodo = Integer.valueOf(strIdTodo); 
            var currentTodo = todoService.serviceGetTodoById(idTodo);
    
            if (currentTodo == null) {
                System.out.println("[!] Todo dengan ID: " + idTodo + ", tidak tersedia.");
                return;
            }
    
            todoService.serviceRemoveTodo(idTodo);
        }
    }

    public void viewUpdateTodo() {
        System.out.println("[Mengubah Todo]");

        var strIdTodo = InputUtil.input("[ID Todo] yang diubah (x Jika Batal)");

        if (!strIdTodo.equals("x")) {
            int idTodo = Integer.valueOf(strIdTodo);
            
            var currentTodo = todoService.serviceGetTodoById(idTodo);

            if (currentTodo == null) {
                System.out.println("[!] Todo dengan ID: " + idTodo + ", tidak tersedia.");
                return;
            }

            System.out.println("[!] Tekan enter untuk menggunakan judul lama.");
            var newTitle = InputUtil.input("Judul baru [Judul lama: " + currentTodo.getTitle() + "]");

            ArrayList<Todo> todos = todoService.serviceGetAllTodos(); 
                for (Todo todo : todos) {
                    if (todo != null && todo.getTitle().equalsIgnoreCase(newTitle)) {
                        System.out.printf("[!] Todo: %s, telah tersedia.\n", newTitle);
                        return;  
                    }
                }

            if (newTitle.isEmpty()) {
                newTitle = currentTodo.getTitle();
            }

                var strStatus = InputUtil.input("Status [0 = Belum Selesai, 1 = Selesai]");
                boolean newStatus;
                if (strStatus.equals("1")) {
                    newStatus = true;  
                } else if (strStatus.equals("0")) {
                    newStatus = false; 
                } else {
                    System.out.println("[!] Status tidak valid, menggunakan status lama.");
                    newStatus = currentTodo.isFinished(); 
                }

                todoService.serviceUpdateTodo(idTodo, newTitle, newStatus); 
                }
            } 


    public void viewSortingTodo()
    {
        System.out.println("[Mengurutkan Todo]");
        System.out.println("Menu:");
        System.out.println("1. Berdasarkan ID (Ascending)");
        System.out.println("2. Berdasarkan ID (Descending)");
        System.out.println("3. Berdasarkan Title (Ascending)");
        System.out.println("4. Berdasarkan Title (Descending)");
        System.out.println("x. Batal");

        var nomor = InputUtil.input("Pilih");

        if(!nomor.equals("x")){
        todoService.serviceSortingTodo(nomor);
        }
        else{
            System.out.println("[!] Tindakan mengurutkan todo dibatalkan.");
        }
    }

    public void viewSearchTodo(){
        System.out.println("[Mencari Todo]");
        var huruf = InputUtil.input("Cari (x Jika Batal)");

        if(!huruf.equals("x")){
            todoService.serviceSearchTodo(huruf);
        }
        else{
            System.out.println("[!] Tindakan mencari todo dibatalkan.");
        }
    }

}