package ua.edu.sumdu.j2se.valeriy.tasks;

import ua.edu.sumdu.j2se.valeriy.tasks.controller.ViewController;
import ua.edu.sumdu.j2se.valeriy.tasks.view.ShowMenu;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args)  {
		AbstractTaskList taskList = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
	/*
	System.out.println(myFile.getAbsolutePath());
	if(myFile.exists())
		System.out.println("File exists");
	else
		System.out.println("File not found");
	TaskIO.readBinary(taskList, myFile);
	System.out.println(taskList);
	*/
	File taskFile = new File("src/main/resources/tasks.txt");
	if(!taskFile.exists()){
		try {
			taskFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	TaskIO.readBinary(taskList,taskFile);
    ViewController control = new ViewController(taskList);
    control.setMenu(ShowMenu.hadMenu);
    for (;;){
    	control.switchMenu(control.getMenu(), taskList);
    	if(control.getMenu()==ShowMenu.workeMenu) {
			for (;;) {
				control.switchMenu(control.getMenu(), taskList);
				if (control.getExit()) {
					control.setExit(false);
					control.setMenu(ShowMenu.hadMenu);
					break;
				}
				if (control.getMenu() == ShowMenu.editMenu){
				    for(;;){
				        control.switchMenu(control.getMenu(), taskList);
				        if(control.getExit()){
				            control.setMenu(ShowMenu.workeMenu);
				            break;
                        }
                    }
                }
			}
		}
		if (control.getExit()) break;
	}

	try {
		taskFile.delete();
		taskFile.createNewFile();
	} catch (IOException e) {
		e.printStackTrace();
	}
	TaskIO.writeBinary(taskList,taskFile);
	/*ShowMenu had = new ShowMenu();
	had.show(had.hadMenu);
	System.out.println();
	had.show(had.workeMenu);
	new ShowTasks();*/
	}
}
