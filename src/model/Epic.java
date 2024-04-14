package model;

import java.util.ArrayList;
import java.util.List;
public class Epic extends Task {
    private final List <Integer>  subtaskIds;
    public Epic(String name, String description) {
        super(name, description);
        subtaskIds = new ArrayList<>();
    }
    public List<Integer> getSubtaskIds() {
        return subtaskIds;
    }
    public void addSubtaskId(Integer subtaskId) {
        subtaskIds.add(subtaskId);
    }
    public void removeSubtaskId(Integer subtaskId) {
        subtaskIds.remove(subtaskId);
    }
    public void clearSubtaskIds() {
        subtaskIds.clear();
    }
}
