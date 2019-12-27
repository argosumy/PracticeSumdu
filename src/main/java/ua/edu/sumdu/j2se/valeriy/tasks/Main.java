package ua.edu.sumdu.j2se.valeriy.tasks;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import static javax.print.attribute.standard.MediaPrintableArea.MM;

public class Main {

	public static void main(String[] args) {
		LocalDateTime now = LocalDateTime.now();
		Task task = new Task("Ak", now.minusSeconds(1),now.plusSeconds(100),20);
		Task task2 = new Task("1111", now.minusSeconds(1),now.plusSeconds(100),20);
		task.setActive(true);
		task2.setActive(true);
		System.out.println(task);
		System.out.println("Now    - " + now);
		System.out.println("Start  - " + now.minusSeconds(1) + " interval " + 20);
		LocalDateTime non = now;
		System.out.println("Repeat - " + task.nextTimeAfter(now.plusSeconds(0)));
		System.out.println("Expect - " + now.plusSeconds(30));
		AbstractTaskList taskList = new ArrayTaskList();
		taskList.add(task);
		taskList.add(task2);
		File file = new File("src/main/resources/tasks.txt");

		try (FileOutputStream out = new FileOutputStream(file)){
			TaskIO.write(taskList, out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (FileOutputStream out = new FileOutputStream(file)){
			TaskIO.write(taskList, out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long q = 2920050200L;
		//Calendar calStart = Calendar.getInstance();
		//calStart.setTimeInMillis(q);
		System.out.println(q);
		//SimpleDateFormat date = new SimpleDateFormat(yyyy-MM-dd-HH-mm-ss.zzz);
		LocalDateTime ww = LocalDateTime.ofEpochSecond(q,0, ZoneOffset.UTC);
		System.out.println(ww);

        //TaskIO.write(taskList, TaskIO.writeBinary(taskList,out));
/*
	    Task task0 = new Task("Inactive     OUT", 0, 1000, 1);
        task0.setActive(false);
        System.out.println(task0);
        //ArrayTaskList taskList1 = new ArrayTaskList();
        LinkedTaskList taskList1 = new LinkedTaskList();
        //System.out.println("нулевой " + taskList1.getArrayList().length);

        taskList1.add(task0);
        Task task1 = new Task("Repeat inside IN", 51, 58, 2);
        task1.setActive(true);
        taskList1.add(task1);
        Task task2 = new Task("Repeat outside IN", 0, 100, 5);
        task2.setActive(true);
        taskList1.add(task2);
        Task task5 = new Task("Repeat outside OUT", 0, 100, 22);
        task5.setActive(true);
        taskList1.add(task5);
        Task task6 = new Task("Repeat left OUT", 0, 40, 1);
        task6.setActive(true);
        taskList1.add(task6);
        Task task7 = new Task("Repeat right OUT", 65, 100, 1);
        task7.setActive(true);
        taskList1.add(task7);
        Task task8 = new Task("Repeat left intersect IN 1", 0, 55, 13);
        task8.setActive(true);
        taskList1.add(task8);
        Task task9 = new Task("Repeat left intersect IN 2", 0, 60, 30);
        task9.setActive(true);
        taskList1.add(task9);
        Task task10 = new Task("Repeat left intersect OUT", 0, 55, 22);
        task10.setActive(true);
        taskList1.add(task10);
        Task task11 = new Task("Repeat right intersect IN", 55, 100, 20);
        task11.setActive(true);
        taskList1.add(task11);
        System.out.println("размер size " + taskList1.size());
        Iterator<Task> it = taskList1.iterator();
        int i = 0;
        while (it.hasNext()){
            System.out.println(i + " -  " + it.next());
            //it.remove();
            i++;
        }
        i=0;
        while (it.hasNext()){
               System.out.println(i + " -  " + it.next());
                //it.remove();
                i++;
            }
        /*for (int i = 0; i <= taskList1.size(); i++) {
                    System.out.println(i + " : " + taskList1.getTask(i));
            }*/
/*        taskList1.remove(task11);
        System.out.println("размер size " + taskList1.size());

            for (i = 0; i <= taskList1.size(); i++) {
                    System.out.println(i + " : " + taskList1.getTask(i));
            }

*/

     //   System.out.println(taskList1.toString());
       // taskListLimit = taskList1.incoming(50,60);
       // System.out.println("размерLim " + taskListLimit.size());

      //  System.out.println(taskList1.remove(task1));

        //System.out.println(taskList1.getArrayList().length);
	}
}
