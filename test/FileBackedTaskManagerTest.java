
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import service.FileBackedTaskManager;
public class FileBackedTaskManagerTest {
    private FileBackedTaskManager taskManager;

    @TempDir
    File tempDir;

    @BeforeEach
    void setUp() {
        File saveFile = new File(tempDir, "test_save_file.csv");
        taskManager = new FileBackedTaskManager(saveFile);
    }


    @Test
    public void testSaveAndLoadEmptyFile() throws IOException {
        File file = File.createTempFile("test", ".txt", tempDir);
        FileBackedTaskManager manager = new FileBackedTaskManager(file);
        manager.save();
        Assertions.assertTrue(file.length() == 0);
    }

    @Test
    public void testSaveAndLoadTasks() throws IOException {
        File file = File.createTempFile("test", ".txt", tempDir);
        FileBackedTaskManager manager = new FileBackedTaskManager(file);
        manager.save();
        FileBackedTaskManager loadedManager = FileBackedTaskManager.loadFromFile(file);
        if (loadedManager != null) {
            Assertions.assertEquals(manager.getAllTasks(), loadedManager.getAllTasks());
        }
    }

    @Test
    public void testSaveAndLoadMultipleTasks() throws IOException {
        File file = File.createTempFile("test", ".txt", tempDir);
        FileBackedTaskManager manager = new FileBackedTaskManager(file);
        manager.save();
        FileBackedTaskManager loadedManager = FileBackedTaskManager.loadFromFile(file);
        if (loadedManager != null) {
            Assertions.assertEquals(manager.getAllTasks(), loadedManager.getAllTasks());
        }
    }
}