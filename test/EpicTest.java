import static org.junit.jupiter.api.Assertions.*;

import model.Epic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.SubTask;



class EpicTest {
    private Epic epic;
    @BeforeEach
    public void setUp() {
        epic = new Epic("Epic 1", "This is an epic task.");

    }

    @Test
    public void testAddSubtaskId() {
        epic.addSubtaskId(1);
        assertTrue(epic.getSubtaskIds().contains(1), "The addSubtaskId method did not add the subtask ID to the list.");
    }
    @Test
    public void testCannotSetItselfAsEpic() {
        Epic epic = new Epic("Epic 1", "This is an epic");
        SubTask subTask = new SubTask("Subtask 1", "This is a subtask", epic.getId());
        Integer result = subTask.setEpicId(subTask.getEpicId());
        assertEquals(Integer.valueOf(0), result);
    }
    @Test
    public void testAddSubtaskId_self() {
        SubTask subTask = new SubTask("SubTask 1", "Description", 1);
        subTask.setEpicId(subTask.getId());
        assertEquals(1, subTask.getEpicId());
    }
    @Test
    public void testSetEpicId_self() {
        Epic epic = new Epic("Epic 1", "Description");
        epic.addSubtaskId(epic.getId());
        assertFalse(epic.getSubtaskIds().isEmpty());
    }

}