package ua.edu.sumdu.j2se.valeriy.tasks.model;

public class TaskListFactory {

    public static AbstractTaskList createTaskList(ListTypes.types type){
        if (type == ListTypes.types.ARRAY) {
            AbstractTaskList taskList = new ArrayTaskList();
            return taskList;
        }
        if (type == ListTypes.types.LINKED){
            AbstractTaskList taskList = new LinkedTaskList();
            return taskList;
        }
        return null;
    }
}
