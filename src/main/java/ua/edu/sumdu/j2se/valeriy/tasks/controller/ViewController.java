package ua.edu.sumdu.j2se.valeriy.tasks.controller;

import ua.edu.sumdu.j2se.valeriy.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.valeriy.tasks.view.ActionTask;
import ua.edu.sumdu.j2se.valeriy.tasks.view.ShowCalendar;
import ua.edu.sumdu.j2se.valeriy.tasks.view.ShowMenu;
import ua.edu.sumdu.j2se.valeriy.tasks.view.ShowTasks;

import java.util.Scanner;

public class ViewController {
    private String [] menu;
    private Boolean exit = false;
    ActionTask actionTask;

    public ViewController(AbstractTaskList taskList) {
        actionTask = new ActionTask(taskList);
    }

    public String[] getMenu() {
        return menu;
    }

    public void setMenu(String[] menu) {
        this.menu = menu;
    }

    public Boolean getExit() {
        return exit;
    }

    public void setExit(Boolean exit) {
        this.exit = exit;
    }

    /**
     * Контроллер методов отображающих пункты меню в консоли, а также
     * вызов действий над задачами (создать задачу, удалить задачу,
     * редактировать задачу, вывести список всех задач в консоль) в зависимости от
     * menu.
     * @param menu массив пунктов menu для вывода в консоль
     * @param taskList текущий список задач
     */
    public void switchMenu(String [] menu, AbstractTaskList taskList){
        setExit(false);
        ShowMenu menuView = new ShowMenu();
        ShowTasks tasksShow = new ShowTasks();
        menuView.show(menu);
        int numAction = readKey();
        System.out.println();
        switch (numAction){
            case 1:
                //Создается новая задача
                if (menu == ShowMenu.hadMenu || menu == ShowMenu.workeMenu) {
                    actionTask.taskAdd(taskList);
                    }
                //editMenu - edit TASK
                if (menu == ShowMenu.editMenu){
                    tasksShow.show(taskList);
                    actionTask.taskEdit(taskList, numAction);
                    }
                break;
            case 2:
                //hadMenu - Вызов workMenu (меню для работы с задачами)
                if(menu == ShowMenu.hadMenu){
                    setMenu(ShowMenu.workeMenu);
                }
                //workMenu - удаление задачи
                if(menu == ShowMenu.workeMenu){
                    if (tasksShow.show(taskList)) {
                        actionTask.taskRemove(taskList);
                    }
                }
                //editMenu - Start/End/Replay TASK
                if(menu == ShowMenu.editMenu){
                    tasksShow.show(taskList);
                    actionTask.taskEdit(taskList, numAction);
                }
                break;
            case 3:
                //hadMenu - Показывает все существующие задачи
                if(menu == ShowMenu.hadMenu){
                    tasksShow.show(taskList);
                }
                //workMenu -> edit Menu
                if(menu == ShowMenu.workeMenu){
                    if (taskList.size() > 0) {
                        setMenu(ShowMenu.editMenu);
                    }
                }
                // Task is Active
                if(menu == ShowMenu.editMenu){
                    tasksShow.show(taskList);
                    actionTask.taskEdit(taskList,numAction);
                }
                break;
            case 4:
                ShowCalendar showCalendar = new ShowCalendar();
                tasksShow.show(taskList);
                showCalendar.show(actionTask.taskСalendar(taskList));
                break;
            case 0:
                setExit(true);
                System.out.println("EXIT");
                break;
            }
    }
    public int readKey() {
        int num;
        while (true) {
            Scanner in = new Scanner(System.in);
            String text = in.next();
            if(text.matches("\\d+")){
                num = Integer.parseInt(text);
                break;
            }
            System.out.print("Повторите ввод..");
        }
        return num;
    }

}
