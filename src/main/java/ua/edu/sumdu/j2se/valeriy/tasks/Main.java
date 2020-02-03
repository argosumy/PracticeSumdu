package ua.edu.sumdu.j2se.valeriy.tasks;

import ua.edu.sumdu.j2se.valeriy.tasks.controller.NotificationController;
import ua.edu.sumdu.j2se.valeriy.tasks.controller.Controller;
import ua.edu.sumdu.j2se.valeriy.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.valeriy.tasks.model.ListTypes;
import ua.edu.sumdu.j2se.valeriy.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.valeriy.tasks.model.TaskListFactory;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args)  {
	AbstractTaskList taskList = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
	File taskFile = new File("src/main/resources/tasks.txt");
	if(!taskFile.exists()){
		try {
			taskFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	TaskIO.readBinary(taskList,taskFile);
	//открытие потока уведомлений
	NotificationController noti = new NotificationController(taskList);
	noti.setDaemon(true);
	noti.start();

	new Controller(taskList);

	//перезапись файла задач
	try {
		taskFile.delete();
		taskFile.createNewFile();
	} catch (IOException e) {
		e.printStackTrace();
	}
	TaskIO.writeBinary(taskList,taskFile);
	}
}
