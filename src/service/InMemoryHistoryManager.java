package service;

import model.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InMemoryHistoryManager implements HistoryManager {
    private final Map<Integer, Node> taskMap;
    private Node head;
    private Node tail;

    public InMemoryHistoryManager() {
        this.taskMap = new HashMap<>();
        this.head = null;
        this.tail = null;
    }
    private void removeNode(Node node) {
        if (node == head) {
        head = node.next;
    }
        if (node == tail) {
        tail = node.prev;
    }
        if (node.prev != null) {
        node.prev.next = node.next;
    }
        if (node.next != null) {
        node.next.prev = node.prev;
    }
}

    @Override
    public void add(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }

        if (taskMap.containsKey(task.getId())) {
            Node existingNode = taskMap.get(task.getId());
            removeNode(existingNode);
        }

        Node newNode = new Node(task);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        taskMap.put(task.getId(), newNode);
    }


    @Override
    public void remove(int id) {
        if (taskMap.containsKey(id)) {
            Node nodeToRemove = taskMap.get(id);
            removeNode(nodeToRemove);
            taskMap.remove(id);
        }
    }


    @Override
    public List<Task> getHistory() {
        List<Task> historyList = new ArrayList<>();
        Node current = head;

        while (current != null) {
            historyList.add(current.task);
            current = current.next;
        }

        return historyList;
    }


    private static class Node {
        Task task;
        Node prev;
        Node next;

        Node(Task task) {
            this.task = task;
            this.prev = null;
            this.next = null;
        }
    }
}

