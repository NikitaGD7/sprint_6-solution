import model.Epic;
import model.SubTask;
import model.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import service.FileBackedTaskManager;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class FileBackedTaskManagerTest {
    private File tempFile;
    private FileBackedTaskManager manager;
    @BeforeEach
    public void setUp() {
        tempFile = new File("test_save.txt");
        manager = new FileBackedTaskManager(tempFile);
    }

    @AfterEach
    void cleanup() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    private File createTempFile() {
        try {
            return File.createTempFile("test", ".csv");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Test
    public void testSaveAndLoadAfterDeletion() {
        Task task1 = new Task(1, "Task 1", "Description 1");
        Task task2 = new Task(2, "Task 2", "Description 2");
        manager.addNewTask(task1);
        manager.addNewTask(task2);

        manager.deleteTask(1);

        manager.save();

        manager = FileBackedTaskManager.loadFromFile(tempFile);
        assertNull(manager.getTask(1));
    }

    @Test
    public void testSaveAndLoadEmptyFile() {
        tempFile = createTempFile();
        FileBackedTaskManager taskManager = new FileBackedTaskManager(tempFile);

        taskManager.save();
        FileBackedTaskManager loadedTaskManager = FileBackedTaskManager.loadFromFile(tempFile);

        assertEquals(0, loadedTaskManager.getAllTasks().size());
    }
}
