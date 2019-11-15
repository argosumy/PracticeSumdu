package ua.edu.sumdu.j2se.valeriy.tasks;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

	    Task task0 = new Task("Inactive     OUT", 0, 1000, 1);
        task0.setActive(false);
        System.out.println(task0);
        ArrayTaskList taskList1 = new ArrayTaskList();
        System.out.println("нулевой " + taskList1.arrayList.length);

        taskList1.add(task0);

        //System.out.println(taskList1);
        System.out.println(taskList1);
        System.out.println("размер " + taskList1.arrayList.length);
        //System.out.println(taskList [0]);

       // Task [] taskList = add(task0);
        //System.out.println(taskList.toString());
        //taskList.add(task0);

        Task task1 = new Task("Simple bound OUT", 50);
        task1.setActive(true);
        taskList1.add(task1);
        System.out.println("1   " + taskList1);
        Task task2 = new Task("Simple bound IN", 60);
        task2.setActive(true);
        taskList1.add(task2);
        Task task3 = new Task("Repeat inside IN", 51, 58, 2);
        task3.setActive(true);
        taskList1.add(task3);
        Task task4 = new Task("Repeat outside IN", 0, 100, 5);
        task4.setActive(true);
        taskList1.add(task4);
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
        System.out.println(taskList1.toString());
       // taskListLimit = taskList1.incoming(50,60);
       // System.out.println("размерLim " + taskListLimit.size());

        System.out.println(taskList1.remove(task1));

        System.out.println(taskList1.arrayList.length);
	}
}
