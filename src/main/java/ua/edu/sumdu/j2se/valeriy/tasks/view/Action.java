package ua.edu.sumdu.j2se.valeriy.tasks.view;

import ua.edu.sumdu.j2se.valeriy.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.valeriy.tasks.model.Task;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public interface Action {
    public void taskAdd(AbstractTaskList taskList);
    public void taskRemove(AbstractTaskList taskList);
    public void taskEdit(AbstractTaskList taskList, int numAction);
    public int readKey();
    public SortedMap<LocalDateTime, Set<Task>> taskСalendar(AbstractTaskList taskList);
}
