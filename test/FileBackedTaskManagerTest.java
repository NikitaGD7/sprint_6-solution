
import model.Task;
import model.Epic;
import model.SubTask;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.IOException;
import service.FileBackedTaskManager;
import service.TaskManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileBackedTaskManagerTest {
    private File tempFile;

    @AfterEach
    void cleanup() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    public void testSaveAndLoadEmptyFile() {
        // Arrange
        tempFile = createTempFile();

        // Act
        FileBackedTaskManager taskManager = new FileBackedTaskManager(tempFile);
        taskManager.save();

        // Assert
        TaskManager loadedTaskManager = FileBackedTaskManager.loadFromFile(tempFile);
        assertEquals(0, loadedTaskManager.getAllTasks().size());
    }

    @Test
    public void testSaveAndLoadMultipleTasks() {
        // Arrange
        tempFile = createTempFile();
        FileBackedTaskManager taskManager = new FileBackedTaskManager(tempFile);

        Task task1 = new Task("Task 1", "Description 1");
        Epic epic1 = new Epic("Epic 1", "In Progress");
        SubTask subTask1 = new SubTask("SubTask 1", "Subtask Description 1", epic1.getId());

        taskManager.addNewTask(task1);
        taskManager.addNewEpic(epic1);
        taskManager.addNewSubtask(subTask1);

        // Act
        taskManager.save();
        TaskManager loadedTaskManager = FileBackedTaskManager.loadFromFile(tempFile);

        // Assert
        assertEquals(3, loadedTaskManager.getAllTasks().size());
    }

    private File createTempFile() {
        try {
            return File.createTempFile("test", ".csv");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}