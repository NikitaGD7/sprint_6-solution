package service;
import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class FileBackedTaskManager extends InMemoryTaskManager {
    public final File saveFile;

    public FileBackedTaskManager (File saveFile) {
        this.saveFile = saveFile;
    }

    public void save() {
        try (FileWriter writer = new FileWriter(saveFile)) {
            writer.write("id,type,name,status,description,epic" + System.lineSeparator());

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
    public static FileBackedTaskManager loadFromFile(File file) {
        FileBackedTaskManager taskManager = new FileBackedTaskManager(file);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                Task task = taskFromString(line);
                taskManager.addNewTask(task);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new ManagerLoadException("Error loading from file: " + file.getName(), e);
        }
        return taskManager;
    }
    public static class ManagerLoadException extends RuntimeException {

        public ManagerLoadException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    public static class ManagerSaveException extends RuntimeException {

        public ManagerSaveException(String message, Throwable cause) {
            super(message, cause);
        }
    }


    private String epicToString(Epic epic) {
        return "";
    }

    private String subtaskToString(SubTask subtask) {
        return "";
    }
    private String taskToString(Task task) {
        return task.getId() + "," + task.getType() + "," + task.getName() + "," + task.getStatus() + "," + task.getDescription();
    }

    public static Task taskFromString(String value) {
        String[] parts = value.split(",");
        int id = Integer.parseInt(parts[0]);
        TaskType type = TaskType.valueOf(parts[1]);
        String name = parts[2];
        String status = parts[3];
        String description = parts[4];
        int epicId = Integer.parseInt(parts[5]);

        if (type == TaskType.EPIC) {
            return new Epic(name, status);
        } else if (type == TaskType.SUBTASK) {
            return new SubTask(name, description, epicId);
        } else {
            return new Task(name, description);
        }
    }

    public static String historyManagerToString(HistoryManager manager) {
        StringBuilder sb = new StringBuilder();
        for (Task task : manager.getHistory()) {
            sb.append(task.getId()).append(",");
        }
        return sb.toString();
    }

    public static List<Integer> historyFromString(String value) {
        List<Integer> taskIds = new ArrayList<>();
        String[] parts = value.split(",");
        for (String part : parts) {
            taskIds.add(Integer.parseInt(part));
        }
        return taskIds;
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
