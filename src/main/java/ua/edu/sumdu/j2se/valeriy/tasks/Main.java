package ua.edu.sumdu.j2se.valeriy.tasks;

import ua.edu.sumdu.j2se.valeriy.tasks.controller.*;
import ua.edu.sumdu.j2se.valeriy.tasks.model.*;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args)  {
	AbstractTaskList taskList = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
	File taskFile = new File("src/main/resources/tasks.txt");
	File taskFileJson = new File("src/main/resources/tasksjson.txt");
	if(!taskFile.exists()){
		try {
			taskFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	if(!taskFile.exists()){
		try {
			taskFileJson.createNewFile();
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
	TaskIO.readBinary(taskList,taskFile);
	//открытие потока уведомлений
	NotificationController noti = new NotificationController(taskList);
	noti.setDaemon(true);
	noti.start();
	//Вызов основного контроллера
	new Controller(taskList);
	}
}
