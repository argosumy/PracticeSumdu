package ua.edu.sumdu.j2se.valeriy.tasks.view;

import ua.edu.sumdu.j2se.valeriy.tasks.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class ShowCalendar implements View {
    /**
     * Выводит на экран отсортированный список запланированных задач по дате
     * в формате дата выполнения / название задачи
     * @param tasksCalendar подготовленный список методом ActionTask.taskCalendar
     * @return не используется
     */
    @Override
    public Boolean show(Object tasksCalendar) {
        SortedMap<LocalDateTime, Set<Task>> tasks = (SortedMap<LocalDateTime, Set<Task>>)tasksCalendar;
        if (tasks.size() != 0 ){
            draw(66);
            String text = "Название задачи";
            String taskTime = "Время начала";
            System.out.printf("%-25s|%-40s%n", taskTime, text);
            for (Map.Entry entry: tasks.entrySet()) {
                draw(66);
                LocalDateTime time = (LocalDateTime) entry.getKey();
                taskTime = time.format(DateTimeFormatter.ofPattern("dd MMMM yyyy'г. в' HH':'mm"));
                Set <Task> taskSet = (Set<Task>) entry.getValue();
                for (Task task: taskSet) {
                   text = task.getTitle();
                    if(taskTime != null){
                        System.out.printf("%25s|%-40s%n", taskTime, text);
                        taskTime = null;
                    }
                    else {
                        System.out.printf("%25s|%-40s%n", "", text);
                    }
                }
            }
            draw(66);
        }
        else {
            System.out.println("НЕТ ЗАДАЧ НА ВАШИ ДАТЫ");
        }
        return null;
    }

    public void draw (int maxTitle){
        for (int i = 0; i < maxTitle; i++){
            System.out.print("-");
        }
        System.out.println();
    }
}
