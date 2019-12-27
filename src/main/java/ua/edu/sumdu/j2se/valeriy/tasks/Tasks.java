package ua.edu.sumdu.j2se.valeriy.tasks;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class Tasks implements Serializable {
    public static final Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        AbstractTaskList taskListLimit;
        if (tasks instanceof ArrayTaskList){
            taskListLimit = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
        }
        else {
            taskListLimit = TaskListFactory.createTaskList(ListTypes.types.LINKED);
        }
        for (Task task: tasks){
            if ((task.isActive()) && (task != null)) {
                if ((task.nextTimeAfter(start) != null)
                    && (task.nextTimeAfter(start).isBefore(end.plusSeconds(1)))){
                    taskListLimit.add(task);
                }
            }
        }
        return taskListLimit;
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end){
        SortedMap<LocalDateTime, Set<Task>> calendarMap = new TreeMap<>();
        Set<Task> set = new HashSet<>();
        for (LocalDateTime i = start; i.isBefore(end.plusSeconds(1)); i = i.plusSeconds(1)){
            for (Task k: tasks) {
                if (k.nextTimeAfter(i.minusSeconds(1)).equals(i)){
                    set.add(k);
                }
            }
            if (set.size() != 0){
                calendarMap.put(i , set);
                set = new HashSet<>();
            }
        }
        return calendarMap;
    }
}
