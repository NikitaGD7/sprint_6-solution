package model;

import java.util.Objects;

public class SubTask extends Task {
    private int epicId;


    public SubTask(int id, String name, String description, int epicId) {
        super(name, description);
        this.epicId = id;
    }

    public Integer getEpicId() {
        return epicId;
    }
    @Override
    public String toString() {
        return super.toString() + " EpicId: " + epicId;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SubTask subTask = (SubTask) obj;
        return super.equals(obj) && epicId == subTask.epicId;
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), epicId);
    }
    public Integer setEpicId(int epicId) {
        this.epicId = epicId;
        return null;
    }
}
