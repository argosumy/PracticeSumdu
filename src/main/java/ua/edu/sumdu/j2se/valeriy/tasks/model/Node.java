package ua.edu.sumdu.j2se.valeriy.tasks.model;

public class Node implements Cloneable {
    private Node nextNode;
    private Task task;

    public Node(){
        nextNode = null;
        this.task = null;
    }

    public Node(Task task) {
        nextNode = null;
        this.task = task;
    }

    public Node(Task task, Node nextNode) {
        this.task = task;
        this.nextNode = nextNode;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public Node clone() throws CloneNotSupportedException {
        return (Node) super.clone();
    }
}
