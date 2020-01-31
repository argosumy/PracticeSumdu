package ua.edu.sumdu.j2se.valeriy.tasks.view;

import ua.edu.sumdu.j2se.valeriy.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.valeriy.tasks.Task;

public class ShowTasks implements View {

    public ShowTasks() {
    }
    @Override
    public Boolean show(Object menu) {
        int i = 1;
        AbstractTaskList taskList = (AbstractTaskList) menu;
        if (taskList.size() == 0) {
            System.out.println("У ВАС нет задач");
            System.out.println();
            return false;
        } else {
            System.out.println("ВАШИ ЗАДАЧИ");
            for (Object task : taskList) {
                System.out.println("Задача № " + i + ": " + task);
                i++;
            }
            return true;
        }
    }
}
