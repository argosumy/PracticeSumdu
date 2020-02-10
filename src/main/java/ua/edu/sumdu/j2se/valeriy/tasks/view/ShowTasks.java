package ua.edu.sumdu.j2se.valeriy.tasks.view;

import ua.edu.sumdu.j2se.valeriy.tasks.model.AbstractTaskList;

public class ShowTasks implements View {

    public ShowTasks() {
    }

    /**
     * Отображает все задачи которые есть в списке задач со всеми полями.
     * @param tasks текущий список задач
     * @return
     */
    @Override
    public Boolean show(Object tasks) {
        int i = 1;
        AbstractTaskList taskList = (AbstractTaskList) tasks;
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
