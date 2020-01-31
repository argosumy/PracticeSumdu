package ua.edu.sumdu.j2se.valeriy.tasks.controller;

import ua.edu.sumdu.j2se.valeriy.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.valeriy.tasks.Task;
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

    public void switchMenu(String [] menu, AbstractTaskList taskList){
           // actionTask.readFile();
            int taskID;
            setExit(false);
            ShowMenu menuView = new ShowMenu();
            ShowTasks tasksShow = new ShowTasks();
            tasksShow.show(taskList);
            menuView.show(menu);
            int numAction = ReadKey.readKey();
            System.out.println();
            switch (numAction){
                case 1://Создается новая задача
                    if (menu == ShowMenu.hadMenu || menu == ShowMenu.workeMenu) {
                        actionTask.taskAdd(taskList);
                    }
                    //editMenu - edit TASK
                    if (menu == ShowMenu.editMenu){
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
                           // tasksShow.show(taskList);
                        }
                    }
                    //editMenu - Start/End/Replay TASK
                    if(menu == ShowMenu.editMenu){
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
                          //  tasksShow.show(taskList);
                         //  actionTask.taskEdit(taskList);
                            setMenu(ShowMenu.editMenu);
                        }
                    }
                    // Task is Active
                    if(menu == ShowMenu.editMenu){
                        actionTask.taskEdit(taskList,numAction);
                    }
                    break;
                case 0:
                        setExit(true);
                        System.out.println("EXIT");
                    break;
            }
    }




    public String readText() {
        String text;
        while (true) {
            Scanner in = new Scanner(System.in);
            text = in.next();
            break;
        }
        return text;
    }

}
