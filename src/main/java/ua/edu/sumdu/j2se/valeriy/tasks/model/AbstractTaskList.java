package ua.edu.sumdu.j2se.valeriy.tasks.model;

import java.io.Serializable;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Cloneable, Iterable<Task>, Serializable {
    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract int size();
    public abstract Task getTask(int index);

    @Override
    protected Object clone() throws CloneNotSupportedException {
        AbstractTaskList cloned = (AbstractTaskList) super.clone();
        return cloned;
    }

    public abstract Stream<Task> getStream();

}
