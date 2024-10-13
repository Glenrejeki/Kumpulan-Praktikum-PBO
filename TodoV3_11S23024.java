import repository.TodoRepository;
import repository.TodoRepositoryImpl;
import service.TodoService;
import service.TodoServiceImpl;
import view.TodoView;
public class TodoV3_11S23026 {

    public static void main(String[] args) {

        TodoRepository todoRepository = new TodoRepositoryImpl();
        TodoService todoService = new TodoServiceImpl(todoRepository);

        TodoView todoView = new TodoView(todoService);

        todoView.viewShowTodo();

    }
}