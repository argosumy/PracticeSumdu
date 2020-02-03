package ua.edu.sumdu.j2se.valeriy.tasks.controller;

import ua.edu.sumdu.j2se.valeriy.tasks.model.AbstractTaskList;

public interface Action {
    public void taskAdd(AbstractTaskList taskList);
    public void taskRemove(AbstractTaskList taskList);
    public void taskEdit(AbstractTaskList taskList, int numAction);
    public int readKey();


}
