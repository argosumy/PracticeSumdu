package ua.edu.sumdu.j2se.valeriy.tasks;

public class Main {

	public static void main(String[] args) {
		Task task = new Task("some",10);
		Task task2 = new Task("some",10,100,20);
		//task = task2;
		//task.setTime(42);
		System.out.println(task.nextTimeAfter(0));
		System.out.println(task.toString());


	}
}
