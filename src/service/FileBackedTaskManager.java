package service;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


    public class FileBackedTaskManager extends InMemoryTaskManager {
        public final File saveFile;
        private HistoryManager historyManager;

        public FileBackedTaskManager(File saveFile) {
            this.saveFile = saveFile;
            this.historyManager = new InMemoryHistoryManager();
        }

        public void save() {
            try (FileWriter writer = new FileWriter(saveFile)) {
                // Write tasks and epics to the file

                // Write history to the file
                writer.write(historyManagerToString(getHistory()));
            } catch (IOException e) {
                throw new RuntimeException("Error saving to file: " + saveFile.getName(), e);
            }
        }

        public static FileBackedTaskManager loadFromFile(File file) {
            FileBackedTaskManager taskManager = new FileBackedTaskManager(file);
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                reader.readLine();
                String line = reader.readLine();
                while (line != null && !line.isEmpty()) {
                    Task task = taskFromString(line);
                    if (task instanceof Epic) {
                        taskManager.addNewEpic((Epic) task);
                    } else if (task instanceof SubTask) {
                        taskManager.addNewSubtask((SubTask) task);
                    } else {
                        taskManager.addNewTask(task);
                    }
                    line = reader.readLine();
                }
                String historyLine = reader.readLine();
                if (historyLine != null && !historyLine.isEmpty()) {
                    List<Integer> historyIds = historyFromString(historyLine);
                    HistoryManager historyManager = new InMemoryHistoryManager();
                    for (Integer id : historyIds) {
                        Task historyTask = taskManager.getTask(id);
                        if (historyTask != null) {
                            historyManager.add(historyTask);
                        }
                    }
                    taskManager.setHistory(historyManager); // Установка истории в объект TaskManager
                }
            } catch (IOException e) {
                throw new RuntimeException("Error loading from file: " + file.getName(), e);
            }
            return taskManager;
        }
    private String epicToString(Epic epic) {
        return epic.getId() + "," + epic.getType() + "," + epic.getName() + "," + epic.getStatus() + "," + epic.getDescription() + "," + epic.getId();
    }

    private String subtaskToString(SubTask subtask) {
        return subtask.getId() + "," + subtask.getType() + "," + subtask.getName() + "," + subtask.getStatus() + "," + subtask.getDescription() + "," + subtask.getEpicId();
    }

    private String taskToString(Task task) {
        return task.getId() + "," + task.getType() + "," + task.getName() + "," + task.getStatus() + "," + task.getDescription() + "," + (task instanceof SubTask ? ((SubTask) task).getEpicId() : "");
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
            return new Epic(name, description);
        } else if (type == TaskType.SUBTASK) {
            return new SubTask(id, name, description, epicId);
        } else {
            return new Task(id, name, description);
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

        @Override
        public Task getTask(int id) {
            Task task = super.getTask(id);
            if (task != null) {
                historyManager.add(task);
                save();
            }
            return task;
        }

    @Override
    public Epic getEpic(int id) {
        Epic epic = super.getEpic(id);
        if (epic != null) {
            save();
        }
        return epic;
    }

    @Override
    public SubTask getSubTask(int id) {
        SubTask subtask = super.getSubTask(id);
        if (subtask != null) {
            save();
        }
        return subtask;
    }
        public void setHistory(HistoryManager historyManager) {
            this.historyManager = historyManager;
        }

}
