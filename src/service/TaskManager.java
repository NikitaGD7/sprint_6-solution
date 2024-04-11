package service;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.List;

public interface TaskManager {

    List<Task> getAllTasks();

    List<SubTask> getSubtasks();

    List<Epic> getEpics();

    List<SubTask> getEpicSubtasks(int epicId);

    Task getTask(int id);

    Epic getEpic(int id);

    SubTask getSubTask(int id);

    int addNewTask(Task task);

    int addNewEpic(Epic epic);

    int addNewSubtask(SubTask subtask);

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubtask(SubTask subtask);

    void deleteTask(int id);

    void deleteEpic(int id);

    void deleteSubtask(int id);

    void deleteTasks();

    void deleteSubtasks();

    void deleteEpics();
    List<Task> getHistory();
}

