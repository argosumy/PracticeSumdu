package ua.edu.sumdu.j2se.valeriy.tasks;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
        ArrayTaskList taskList = new ArrayTaskList();
	    Task task0 = new Task("Inactive     OUT", 0, 1000, 1);
        task0.setActive(false);
        taskList.add(task0);
        Task task1 = new Task("Simple bound OUT", 50);
        task1.setActive(true);
        taskList.add(task1);
        Task task2 = new Task("Simple bound IN", 60);
        task2.setActive(true);
        taskList.add(task2);
        Task task3 = new Task("Repeat inside IN", 51, 58, 2);
        task3.setActive(true);
        taskList.add(task3);
        Task task4 = new Task("Repeat outside IN", 0, 100, 5);
        task4.setActive(true);
        taskList.add(task4);
        Task task5 = new Task("Repeat outside OUT", 0, 100, 22);
        task5.setActive(true);
        taskList.add(task5);
        Task task6 = new Task("Repeat left OUT", 0, 40, 1);
        task6.setActive(true);
        taskList.add(task6);
        Task task7 = new Task("Repeat right OUT", 65, 100, 1);
        task7.setActive(true);
        taskList.add(task7);
        Task task8 = new Task("Repeat left intersect IN 1", 0, 55, 13);
        task8.setActive(true);
        taskList.add(task8);
        Task task9 = new Task("Repeat left intersect IN 2", 0, 60, 30);
        task9.setActive(true);
        taskList.add(task9);
        Task task10 = new Task("Repeat left intersect OUT", 0, 55, 22);
        task10.setActive(true);
        taskList.add(task10);
        Task task11 = new Task("Repeat right intersect IN", 55, 100, 20);
        task11.setActive(true);
        taskList.add(task11);
        System.out.println("размер1 " + taskList.size());

        ArrayTaskList taskListLimit = new ArrayTaskList();
        taskListLimit = taskList.incoming(50,60);
        System.out.println("размерLim " + taskListLimit.size());
/*
        task3.nextTimeAfter(5);
        System.out.println();



        System.out.println(taskList.getTask(0));
        System.out.println("размер1 " + taskList.size());

        taskList.add(task2);
        System.out.println(taskList.toString());

        System.out.println("Limit " + taskListLimit.toString());
        //System.out.println(taskList.get(0));*/
       /* for (Task k: taskList) {
            System.out.println(k);
        }*/
		//task = task2;
		//task.setTime(42);
		//System.out.println(task.nextTimeAfter(0));
		//System.out.println(task.toString());
       // ArrayTaskList<Task> list = new ArrayTaskList<Task>();
        //ArrayTaskList<Task> arrayTaskList = new ArrayTaskList<Task>();
       /* ArrayTaskList(ArrayList<Task> list1);
        System.out.println("размерT " + arrayTaskList.size());
        arrayTaskList.add(task);
        arrayTaskList.add(task2);
        System.out.println("размер1 " + arrayTaskList.size());
        System.out.println("Remove " + arrayTaskList.remove(task3));
        arrayTaskList.add(task1);
        for (Task k: arrayTaskList) {
            System.out.println(k);
        }
        arrayTaskList.add(1,task);
        arrayTaskList.add(1,task2);

        System.out.println();
        for (Task k: arrayTaskList) {
            System.out.println(k);
        }
        System.out.println("размер" + arrayTaskList.size());*/
        //System.out.println( arrayTaskList.get(0));

	}
}
