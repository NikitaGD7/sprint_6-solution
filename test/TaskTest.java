import static org.junit.jupiter.api.Assertions.*;

import model.Task;
import org.junit.jupiter.api.Test;
import service.InMemoryTaskManager;

class TaskTest {
    @Test
    public void testTaskEqualityById() {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();
        Task task1 = new Task("Model.Task 1", "NEW");
        Task task2 = new Task("Model.Task 2", "NEW");
        Task task3 = new Task("Model.Task 3", "NEW");

        int id1 = taskManager.addNewTask(task1);
        int id2 = taskManager.addNewTask(task2);
        int id3 = taskManager.addNewTask(task3);

        Task retrievedTask1 = taskManager.getTask(id1);
        Task retrievedTask2 = taskManager.getTask(id2);
        Task retrievedTask3 = taskManager.getTask(id3);

        assertEquals(task1, retrievedTask1);
        assertEquals(task2, retrievedTask2);
        assertEquals(task3, retrievedTask3);
    }

}