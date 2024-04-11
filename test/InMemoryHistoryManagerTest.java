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
    public void testHistorySizeLimit() {
        for (int i = 1; i <= 10; i++) {
            Task task = new Task("Model.Task", "IN_PROGRESS");
            historyManager.add(task);
        }
        Task extraTask = new Task("Model.Task", "NEW");
        historyManager.add(extraTask);
        assertTrue(historyManager.getHistory().contains(extraTask), "The extra task was not added to the history.");
    }
    @Test
    public void testAddTask() {
        Task task = new Task("Model.Task", "NEW");
        historyManager.add(task);
        assertTrue(historyManager.getHistory().contains(task), "The task was not added to the history.");
    }
    @Test
    public void testAddNullTaskException() {
        assertThrows(IllegalArgumentException.class, () -> {
            historyManager.add(null);
        }, "Expected an exception when adding a null task.");
    }


}