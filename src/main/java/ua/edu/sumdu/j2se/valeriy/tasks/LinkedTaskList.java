package ua.edu.sumdu.j2se.valeriy.tasks;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public class LinkedTaskList extends AbstractTaskList  {
    private int count;
    private Node actualNode;
    /*
     * construction
     * */
    public LinkedTaskList() {
        count = 0;
        actualNode = new Node(null);
    }

    public Node getActualNode() {
        return actualNode;
    }

    public LinkedTaskList(Node actualNode) {
        this.actualNode = actualNode;
    }

    public void setActualNode(Node actualNode) {
        this.actualNode = actualNode;
    }

    @Override
    public void add(Task task)throws NullPointerException{
        Node newNode = new Node(task);
        Node actualNode1 = actualNode;

        while (actualNode1.getNextNode() != null){
            actualNode1 = actualNode1.getNextNode();
        }
        newNode.setNextNode(actualNode1.getNextNode());
        actualNode1.setNextNode(newNode);
        count++;
    }

    @Override
    public boolean remove(Task task) {
        if (actualNode == null) {
            return false;
        }
        Node actualNode1 = actualNode;
        int i = 0;
        while (actualNode1.getNextNode().getNextNode() != null) {
            if (actualNode1.getNextNode().getTask() == task) {
                if (i == 0) {
                    actualNode.setNextNode(actualNode1.getNextNode().getNextNode());
                    count--;
                    return true;
                }
                if (actualNode1.getNextNode().getNextNode() != null) {
                    actualNode1.setNextNode(actualNode1.getNextNode().getNextNode());
                    count--;
                    return true;
                }
            }
            if ((actualNode1.getNextNode().getNextNode().getNextNode() == null) &
                    (actualNode1.getNextNode().getNextNode().getTask() == task)){
                actualNode1.getNextNode().setNextNode(null);
                count--;
                return true;
            }
            actualNode1 = actualNode1.getNextNode();
            i++;
        }
        return false;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException
    {
        if (index < 0) {
            return null;
        }
        Node current = null;
        if (actualNode != null) {
            current = actualNode.getNextNode();
            for (int i = 0; i < index; i++) {
                if (current.getNextNode() == null) {
                    return null;
                }
                current = current.getNextNode();
            }
            return current.getTask();
        }
        throw new IndexOutOfBoundsException("Index out of Bounds");
    }

    @Override
    public boolean equals(Object o) {
        if (this.hashCode() == o.hashCode()) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(actualNode.getTask());
    }

    @Override
    public LinkedTaskList clone() throws CloneNotSupportedException {
        LinkedTaskList cloneList = (LinkedTaskList) super.clone();
        Node actualNode = (Node) this.getActualNode().clone();
        cloneList.setActualNode(actualNode);
        return cloneList;
    }

    @Override
    public String toString() {
        return "LinkedTaskList{" +
                "count=" + count +
                ", actualNode=" + actualNode +
                "} " + super.toString();
    }

    //Итератор
    @Override
    public Iterator<Object> iterator() {
        return new Iterator<Object>() {
            private Node current = actualNode;// = (Node) actualNode.clone();
            boolean marker;
            @Override
            public boolean hasNext() {
                return (current.getNextNode()!=null);
            }
            @Override
            public Task next()throws IndexOutOfBoundsException {
                marker = true;
                Task next = current.getTask();
                if(hasNext()) {
                    //Task next = current.getNextNode().getTask();
                    if(!hasNext()){
                        throw new IndexOutOfBoundsException();
                    }
                   // remove = current;
                    current = current.getNextNode();
                    next = current.getTask();
                    return next;
                }
                if ((!hasNext())&(next()!=null)){ //возврат последнего элемента в коллекции
                    return next();
                }
                return next;
            }
            @Override
            public void remove() throws IllegalStateException {
                if (marker){        //проверка вызова метода NEXT
                      LinkedTaskList.this.remove(current.getTask());
                }
                else {
                    throw new IllegalStateException("Виклик Iterator.remove без next");
                }
            }
        };
    }

    @Override
    public Stream<Task> getStream() {
        Task [] array = new Task[size()];
        for(int i = 0; i < size(); i++){
            array[i] = this.getTask(i);
        }
        Stream<Task> stream = Stream.of(array);
        return stream;
    }
}

