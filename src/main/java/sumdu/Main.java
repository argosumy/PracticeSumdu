package sumdu;
import sumdu.netcracker.ua.sumdu.j2se.valeriy.tasks.Task;


public class Main {

	public static void main(String[] args) {
		Task task2 =new Task("Meeting",10,100,20);
		task2.setActive(true);
		int current = 11;
		int next2 = task2.nextTimeAfter(current);
		System.out.println("After current time " + current + " Next run in "+next2);
		System.out.println(task2.toString());
	}
}
