package ua.edu.sumdu.j2se.valeriy.tasks.view;

import ua.edu.sumdu.j2se.valeriy.tasks.model.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ShowNotification implements View {
    /**
     * Выводит список задач в виде таблицы в формате
     * Название задачи и время ее начала
     * @param taskList текущий список задач
     * @return не используется в приложение
     */
    @Override
    public Boolean show(Object taskList) {
        AbstractTaskList taskList1 = (AbstractTaskList) taskList;
        int maxTitle = 40;
        int size = taskList1.size();
        String text = "Название задачи";
        String taskTime = "Время начала";

        System.out.println();
        System.out.println("ОБРАТИТЕ ВНИМАНИЕ");
        draw(66);
        System.out.printf("%n%-40s|%10s%n", text, taskTime);
        for (Task t : taskList1) {
            draw(66);
            taskTime = t.nextTimeAfter(LocalDateTime.now()).format(DateTimeFormatter.ofPattern("dd MMMM yyyy'г. в' HH':'mm"));
            if (t.getTitle().length() > maxTitle){
                text = t.getTitle().substring(0,maxTitle);
            }
            else text = t.getTitle();
            System.out.printf("%n%-40s|%10s%n", text, taskTime);
        }
        draw(66);
        System.out.println("");
        System.out.println("Всего задач: " + size);
        for(int i = 0; i < taskList1.size() + 6;i++){
            System.out.print("\r");
        }
        return null;
    }

    public void draw (int maxTitle){
        for (int i = 0; i < maxTitle; i++){
            System.out.print("-");
        }
    }
}
