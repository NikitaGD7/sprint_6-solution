import org.junit.jupiter.api.Test;
import service.HistoryManager;
import service.InMemoryTaskManager;
import service.Managers;
import service.TaskManager;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {

    @Test
    void getDefaultHistory() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        assertNotNull(historyManager);
    }
    @Test
    public void getDefault_returnsInMemoryTaskManager() {
        TaskManager taskManager = Managers.getDefault();
        assertTrue(taskManager instanceof InMemoryTaskManager);
    }
}