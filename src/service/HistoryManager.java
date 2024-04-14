package service;

import model.Epic;
import model.SubTask;
import model.Task;
import model.TaskStatus;

import java.util.List;
public interface HistoryManager {
    void add(Task task);
    List<Task> getHistory();
    void remove(int id);

    void removeAllTasks();

    void removeAllEpics();

    void removeAllSubtasks();
}
