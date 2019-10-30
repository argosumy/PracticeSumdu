package ua.edu.sumdu.j2se.valeriy.tasks;

import java.util.ArrayList;

public class ArrayTaskList {
    public ArrayList<Task> taskList;
   // public ArrayList<Task> taskListLimit;

    @Override
    public String toString() {
        return "ArrayTaskList{"
                + "taskList="
                + taskList
                + '}';
    }

    public ArrayTaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void add(Task task) {
        taskList.add(task);

    }

    public boolean remove(Task task) {
        int size = size();
        taskList.remove(task);
        if (size == size()) {
            return false;
        }
        else {
            return true;
        }
    }

    public int size() {
        return taskList.size();
    }

   public Task getTask(int index) {
        return taskList.get(index);
   }

   public ArrayTaskList incoming(int from, int to) {
       ArrayTaskList taskListLimit = new ArrayTaskList();
       for (Task k:this.taskList) {
           for (int i = from; i < to; i++) {
               if (!k.isActive()) {
                   break;
               }
               if ((k.nextTimeAfter(i) != -1) && (k.nextTimeAfter(i)) < to) {
                    System.out.println(k);
                    taskListLimit.add(k);
                    break;
               }
           }
       }
       return taskListLimit;
    }



}
