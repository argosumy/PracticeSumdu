package ua.edu.sumdu.j2se.valeriy.tasks;

public abstract class AbstractTaskList {
    public abstract void add(Task task);
    public abstract boolean remove(Task task);
    public abstract int size();
    public abstract Task getTask(int index);

    public AbstractTaskList incoming(int from, int to) {
        AbstractTaskList taskListLimit;
        if(this instanceof ArrayTaskList){
            taskListLimit = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
        }
        else {
            taskListLimit = TaskListFactory.createTaskList(ListTypes.types.LINKED);
        }

        for (int i =0; i < this.size(); i++) {
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
        }
        return taskListLimit;
    }
}
