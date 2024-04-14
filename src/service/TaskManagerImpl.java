package service;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.List;

public class TaskManagerImpl implements TaskManager {
    @Override
    public List<Task> getAllTasks() {
        return null;
    }

    @Override
    public List<SubTask> getSubtasks() {
        return null;
    }

    @Override
    public List<Epic> getEpics() {
        return null;
    }

    @Override
    public List<SubTask> getEpicSubtasks(int epicId) {
        return null;
    }

    @Override
    public Task getTask(int id) {
        return null;
    }

    @Override
    public Epic getEpic(int id) {
        return null;
    }

    @Override
    public SubTask getSubTask(int id) {
        return null;
    }

    @Override
    public int addNewTask(Task task) {
        return 0;
    }

    @Override
    public int addNewEpic(Epic epic) {
        return 0;
    }

    @Override
    public int addNewSubtask(SubTask subtask) {
        return 0;
    }

    @Override
    public void updateTask(Task task) {

    }

    @Override
    public void updateEpic(Epic epic) {

    }

    @Override
    public void updateSubtask(SubTask subtask) {

    }

    @Override
    public void deleteTask(int id) {

    }

    @Override
    public void deleteEpic(int id) {

    }

    @Override
    public void deleteSubtask(int id) {

    }

    @Override
    public void deleteTasks() {

    }

    @Override
    public void deleteSubtasks() {

    }

    @Override
    public void deleteEpics() {

    }

    @Override
    public List<Task> getHistory() {
        return null;
    }
}
