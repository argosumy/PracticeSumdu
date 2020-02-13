package ua.edu.sumdu.j2se.valeriy.tasks;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.valeriy.tasks.controller.*;
import ua.edu.sumdu.j2se.valeriy.tasks.model.*;

import java.io.File;
import java.io.IOException;

public class Main {
	private static final Logger logger = Logger.getLogger(TaskIO.class );
	public static void main(String[] args)  {
	AbstractTaskList taskList = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
	File taskFileJson = new File("src/main/resources/tasksjson.txt");
	if(!taskFileJson.exists()){
		try {
			taskFileJson.createNewFile();
		} catch (IOException e) {
				logger.error(e);
		}
	}
	TaskIO.readText(taskList,taskFileJson);
	//открытие потока уведомлений
	NotificationController noti = new NotificationController(taskList);
	noti.setDaemon(true);
	noti.start();
	//Вызов основного контроллера
	new Controller(taskList);
	}
}
