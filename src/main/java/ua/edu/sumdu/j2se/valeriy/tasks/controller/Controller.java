package ua.edu.sumdu.j2se.valeriy.tasks.controller;

import ua.edu.sumdu.j2se.valeriy.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.valeriy.tasks.view.ShowMenu;

public class Controller {
    ViewController control;

    public Controller(AbstractTaskList taskList) {
        control = new ViewController(taskList);
        control.setMenu(ShowMenu.hadMenu);
        for (;;) {
            control.switchMenu(control.getMenu(), taskList);
            if (control.getMenu() == ShowMenu.workeMenu) {
                for (;;) {
                    control.switchMenu(control.getMenu(), taskList);
                    if (control.getExit()) {
                        control.setExit(false);
                        control.setMenu(ShowMenu.hadMenu);
                        break;
                    }
                    if (control.getMenu() == ShowMenu.editMenu) {
                        for (;;) {
                            control.switchMenu(control.getMenu(), taskList);
                            if (control.getExit()) {
                                control.setMenu(ShowMenu.workeMenu);
                                break;
                            }
                        }
                    }
                }
            }
            if (control.getExit()) break;
        }
    }
}
