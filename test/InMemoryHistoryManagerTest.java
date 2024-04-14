import static org.junit.jupiter.api.Assertions.*;

import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.HistoryManager;
import service.InMemoryHistoryManager;

import java.util.List;


class InMemoryHistoryManagerTest {
    private HistoryManager historyManager;

    @BeforeEach
    public void setUp() {
        historyManager = new InMemoryHistoryManager();
    }
    @Test
    public void testGetHistory() {
        Task task = new Task("Model.Task", "NEW");
        historyManager.add(task);
        List<Task> history = historyManager.getHistory();
        assertTrue(historyManager.getHistory().contains(task), "The original history list was modified.");
    }
    @Test
    void testAddTask() {
        Task task1 = new Task("Task 1", "NEW");
        historyManager.add(task1);
        assertEquals(1, historyManager.getHistory().size());
    }

    @Test
    void testAddTaskWithSameId() {
        Task task1 = new Task("Task 1", "NEW");
        Task task2 = new Task("Task 2", "NEW");
        historyManager.add(task1);
        historyManager.add(task2);
        assertEquals(1, historyManager.getHistory().size());
    }

    @Test
    void testRemoveTask() {
        Task task1 = new Task("Task 1", "NEW");
        historyManager.add(task1);
        historyManager.remove(task1.getId());
        assertEquals(0, historyManager.getHistory().size());
    }
    @Test
    public void testAddNullTaskException() {
        assertThrows(IllegalArgumentException.class, () -> {
            historyManager.add(null);
        }, "Expected an exception when adding a null task.");
    }

    @Test
    void testRemoveNonExistentTask() {
        historyManager.remove(1);
        assertEquals(0, historyManager.getHistory().size());
    }
}