package ua.edu.sumdu.j2se.valeriy.tasks.controller;

import ua.edu.sumdu.j2se.valeriy.tasks.AbstractTaskList;
import ua.edu.sumdu.j2se.valeriy.tasks.Task;

import java.util.Scanner;

public interface Action {
    public void taskAdd(AbstractTaskList taskList);
    public void taskRemove(AbstractTaskList taskList);
    public void taskEdit(AbstractTaskList taskList, int numAction);
    public int readKey();
//    public void readFile();
//   public void writeFile();




}
