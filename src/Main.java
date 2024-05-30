import model.Epic;
import model.SubTask;
import model.Task;
import service.InMemoryTaskManager;

public class Main {
    public static void main(String[] args) {
        InMemoryTaskManager tracker = new InMemoryTaskManager();

        // Создание задач
        Task task1 = new Task("Task 1", "NEW");
        Task task2 = new Task("Task 2", "NEW");

        // Добавление задач в трекер
        int taskId1 = tracker.addNewTask(task1);
        int taskId2 = tracker.addNewTask(task2);
        // Создание эпиков
        Epic epic1 = new Epic("Epic 1", "NEW");
        Epic epic2 = new Epic("Epic 2", "IN_PROGRESS");

        // Добавление подзадач в эпик
        epic1.addSubtaskId(task1.getId());
        epic2.addSubtaskId(task2.getId());

        // Добавление эпиков в трекер
        int epicId1 = tracker.addNewEpic(epic1);
        int epicId2 = tracker.addNewEpic(epic2);

        // Создание подзадач
        SubTask subtask1 = new SubTask(1, "Subtask 1", "DONE", epicId1);
        SubTask subtask2 = new SubTask(2, "Subtask 2", "IN_PROGRESS", epicId1);
        SubTask subtask3 = new SubTask(3, "Subtask 3", "NEW", epicId2);

        // Печать списков эпиков, задач и подзадач
        System.out.println("Epics: " + tracker.getEpics());
        System.out.println("Tasks: " + tracker.getAllTasks());
        System.out.println("Subtasks: " + tracker.getSubtasks());

        // Изменение статусов задач и подзадач
        tracker.updateTask(task1);
        tracker.updateTask(task2);
        tracker.updateSubtask(subtask1);
        tracker.updateSubtask(subtask2);
        tracker.updateSubtask(subtask3);

        // Печать измененных статусов
        System.out.println("Updated Epics: " + tracker.getEpics());
        System.out.println("Updated Tasks: " + tracker.getAllTasks());
        System.out.println("Updated Subtasks: " + tracker.getSubtasks());

        // Удаление задачи и эпика
        tracker.deleteTask(taskId2);
        tracker.deleteEpic(epicId1);

        // Печать списков после удаления
        System.out.println("Epics after deletion: " + tracker.getEpics());
        System.out.println("Tasks after deletion: " + tracker.getAllTasks());
        System.out.println("Subtasks after deletion: " + tracker.getSubtasks());
    }
}
