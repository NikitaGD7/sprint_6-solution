import model.Epic;
import model.SubTask;
import model.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import service.FileBackedTaskManager;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileBackedTaskManagerTest {
    private File tempFile;

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
    public void testSaveAndLoadEmptyFile() {
        tempFile = createTempFile();
        FileBackedTaskManager taskManager = new FileBackedTaskManager(tempFile);

        taskManager.save();
        FileBackedTaskManager loadedTaskManager = FileBackedTaskManager.loadFromFile(tempFile);

        assertEquals(0, loadedTaskManager.getAllTasks().size());
    }

    @Test
    public void testSaveAndLoadMultipleTasks() {
        tempFile = createTempFile();
        FileBackedTaskManager taskManager = new FileBackedTaskManager(tempFile);

        Task task1 = new Task("Task 1", "Description 1");
        Epic epic1 = new Epic("Epic 1", "In Progress");
        SubTask subTask1 = new SubTask("SubTask 1", "Subtask Description 1", epic1.getId());

        taskManager.addNewTask(task1);
        taskManager.addNewEpic(epic1);
        taskManager.addNewSubtask(subTask1);

        taskManager.save();
        FileBackedTaskManager loadedTaskManager = FileBackedTaskManager.loadFromFile(tempFile);

        // Проверка количества задач
        assertEquals(3, loadedTaskManager.getAllTasks().size());

        // Проверка конкретных задач
        assertTrue(loadedTaskManager.getAllTasks().contains(task1));
        assertTrue(loadedTaskManager.getEpics().contains(epic1));
        assertTrue(loadedTaskManager.getSubtasks().contains(subTask1));

        // Проверка истории
        assertEquals(3, loadedTaskManager.getHistory().getHistory().size());

    }
}