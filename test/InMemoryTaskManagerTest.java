import static org.junit.jupiter.api.Assertions.*;

import model.Epic;
import model.SubTask;
import model.Task;
import org.junit.jupiter.api.Test;
import service.InMemoryTaskManager;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;

class InMemoryTaskManagerTest {
    private InMemoryTaskManager taskManager;
    @BeforeEach
    public void setUp() {
        taskManager = new InMemoryTaskManager();
    }

    @Test
    public void testGetTask() {
        Task task = new Task("Task", "NEW");
        int taskId = taskManager.addNewTask(task);
        Task retrievedTask = taskManager.getTask(taskId);
        assertEquals(task, retrievedTask, "The retrieved task is not the same as the added task.");
    }
    @Test
    public void testConflictBetweenAssignedAndGeneratedTaskIds() {
        Task task1 = new Task("Model.Task 1", "NEW");
        task1.setId(1);
        taskManager.addNewTask(task1);
        assertEquals(1, taskManager.getTask(1).getId());

        Task task2 = new Task("Model.Task 2", "NEW");
        int generatedId = taskManager.addNewTask(task2);
        assertEquals(2, taskManager.getTask(generatedId).getId());
    }
    @Test
    public void testGetEpic() {
        Epic epic = new Epic("Epic", "NEW");
        int epicId = taskManager.addNewEpic(epic);
        Epic retrievedEpic = taskManager.getEpic(epicId);
        assertEquals(epic, retrievedEpic, "The retrieved epic is not the same as the added epic.");
    }
    @Test
    public void testGetSubtask() {
        Epic epic = new Epic("Epic 1", "This is an epic");
        taskManager.addNewEpic(epic);
        SubTask subtask1 = new SubTask("Subtask 1", "NEW", epic.getId());
        SubTask subtask2 = new SubTask("Subtask 2", "IN_PROGRESS", epic.getId());
        taskManager.addNewSubtask(subtask1);
        taskManager.addNewSubtask(subtask2);
        List<SubTask> subtasks = taskManager.getSubtasks();
        assertTrue(subtasks.contains(subtask1), "The subtasks list does not contain subtask1.");
        assertTrue(subtasks.contains(subtask2), "The subtasks list does not contain subtask2.");
    }
    @Test
    public void testGetSubTask() {
        Epic epic = new Epic("Epic 1", "This is an epic");
        taskManager.addNewEpic(epic);
        SubTask subtask = new SubTask("Subtask 1", "This is a subtask", epic.getId());
        int subtaskId = taskManager.addNewSubtask(subtask);
        SubTask retrievedSubTask = taskManager.getSubTask(subtaskId);
        assertEquals(subtask, retrievedSubTask, "The retrieved subtask is not the same as the added subtask.");
    }


}