package service;
import model.Epic;
import model.SubTask;
import model.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
public class FileBackedTaskManager extends InMemoryTaskManager {
    public final File saveFile;

    public FileBackedTaskManager(File saveFile) {
        this.saveFile = saveFile;
    }

    public void save() {
        try (FileWriter writer = new FileWriter(saveFile)) {

            for (Task task : getAllTasks()) {
                writer.write(taskToString(task) + System.lineSeparator());
            }

            for (Epic epic : getEpics()) {
                writer.write(epicToString(epic) + System.lineSeparator());
            }

            for (SubTask subtask : getSubtasks()) {
                writer.write(subtaskToString(subtask) + System.lineSeparator());
            }

            writer.write(historyManagerToString(getHistory()));
        } catch (IOException e) {
            throw new ManagerSaveException("Error saving to file: " + saveFile.getName(), e);
        }
    }
    public static class ManagerSaveException extends RuntimeException {

        public ManagerSaveException(String message) {
            super(message);
        }

        public ManagerSaveException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    private String taskToString(Task task) {
        return "";
    }

    private String epicToString(Epic epic) {
        return "";
    }

    private String subtaskToString(SubTask subtask) {
        return "";
    }

    private String historyManagerToString(List<Task> history) {
        return "";
    }

    public static FileBackedTaskManager loadFromFile(File file) {
        return null;
    }

    @Override
    public int addNewTask(Task task) {
        int taskId = super.addNewTask(task);
        save();
        return taskId;
    }

    @Override
    public int addNewEpic(Epic epic) {
        int epicId = super.addNewEpic(epic);
        save();
        return epicId;
    }

    @Override
    public int addNewSubtask(SubTask subtask) {
        int subtaskId = super.addNewSubtask(subtask);
        save();
        return subtaskId;
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        save();
    }

    @Override
    public void updateEpic(Epic epic) {
        super.updateEpic(epic);
        save();
    }

    @Override
    public void updateSubtask(SubTask subtask) {
        super.updateSubtask(subtask);
        save();
    }

    @Override
    public void deleteTask(int id) {
        super.deleteTask(id);
        save();
    }

    @Override
    public void deleteEpic(int id) {
        super.deleteEpic(id);
        save();
    }

    @Override
    public void deleteSubtask(int id) {
        super.deleteSubtask(id);
        save();
    }

    @Override
    public void deleteTasks() {
        super.deleteTasks();
        save();
    }

    @Override
    public void deleteSubtasks() {
        super.deleteSubtasks();
        save();
    }

    @Override
    public void deleteEpics() {
        super.deleteEpics();
        save();
    }
}
