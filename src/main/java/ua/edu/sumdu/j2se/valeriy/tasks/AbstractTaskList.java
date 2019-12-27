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

    /* public final AbstractTaskList incoming(LocalDateTime from, LocalDateTime to) {
        AbstractTaskList taskListLimit;
        if(this instanceof ArrayTaskList){
            taskListLimit = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
        }
        else {
            taskListLimit = TaskListFactory.createTaskList(ListTypes.types.LINKED);
        }
        this.getStream().filter(task -> (task.isActive() & (task != null) & (task.nextTimeAfter(from) != null) & (task.nextTimeAfter(from).isBefore(to))))
                .forEach(task -> taskListLimit.add(task));
        /*for (int i =0; i < this.size(); i++) {
            for (int j = from; j < to; j++) {
                if ((!this.getTask(i).isActive()) | (this.getTask(i) != null)) {
                    break;
                }
                if ((this.getTask(i).nextTimeAfter(i) != -1) &&
                        (this.getTask(i).nextTimeAfter(i)) < to) {
                    taskListLimit.add(this.getTask(i));
                    break;
                }
            }
        }*/
      /*  return taskListLimit;
    }*/

    public abstract Stream<Task> getStream();

}
