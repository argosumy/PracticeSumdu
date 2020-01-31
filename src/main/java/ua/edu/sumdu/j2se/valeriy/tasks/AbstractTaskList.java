package ua.edu.sumdu.j2se.valeriy.tasks;

import java.util.Iterator;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Cloneable, Iterator, Iterable{
    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract int size();
    public abstract Task getTask(int index);

    @Override
    protected Object clone() throws CloneNotSupportedException {
        AbstractTaskList cloned = (AbstractTaskList) super.clone();
        return cloned;
    }

    @Override
    public abstract Iterator<Object> iterator();

    @Override
    public void remove() {
    }

    @Override
    public boolean hasNext() {
        if(iterator().hasNext()){
            return true;
        }
        return false;
    }

    @Override
    public Object next() {
        return null;
    }


    public abstract Stream<Task> getStream();

}
