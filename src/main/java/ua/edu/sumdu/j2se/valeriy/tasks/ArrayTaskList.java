package ua.edu.sumdu.j2se.valeriy.tasks;

import java.util.Arrays;

public class ArrayTaskList extends AbstractTaskList {
    private Task []  arrayList;
    private Task [] arrayListNew;
/*
* construction
* */
    public ArrayTaskList() {
        arrayList = new Task[10];
    }
/*
*
* geter and seter arrayList
*
* */
    public Task[] getArrayList() {
        return arrayList;
    }
    public void setArrayList(Task[] arrayList) {
        this.arrayList = arrayList;
    }
    @Override
    public void add(Task task) throws NullPointerException {
        if (task == null) {
            throw new NullPointerException("Задача не может быть null ");
        }
        if (size() == 0) {
            arrayListNew = new Task[1];
            arrayList = arrayListNew;
            arrayList [0] = task;
        }
        else {
            if (arrayList.length == size()) {
                arrayListNew = new Task [size() + 1];
            }
            int i = 0;
            for (Task k : arrayList) {
                if (k == null) {
                    break;}
                arrayListNew [i] = k;
                i++;
            }
            arrayListNew [size()] = task;
            this.arrayList = arrayListNew;
        }
    }
    @Override
    public boolean remove(Task task) throws NullPointerException {
        if (task == null) {
            throw new NullPointerException("Задача не может быть null ");
        }
        boolean removTask = false; //проверка удаления
        //создаем промежуточный массив
        arrayListNew = new Task [arrayList.length];
        int j = 0;
        for (Task k: arrayList) {
            if (( k != task ) | ( removTask )) {
                arrayListNew [j] = k;
                j++;
            }
            else {
                removTask = true;
            }
        }
        //данные из промежуточного массива переписываем в основной
        arrayList = arrayListNew;
        return removTask;
    }
    @Override
    public int size() {
        int i = 0;
        for (Task k:arrayList) {
            if (k != null){
                i++;
            }
        }
        return i;
    }
    @Override
    public Task getTask(int index)throws IndexOutOfBoundsException  {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Ошибка. Задачи с индексом");
        }
        Task task;
        task = null;

        try {
            task = arrayList[index];
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Ошибка. Задачи с индексом " + index + " нет");
        }
        return task;
    }
    @Override
    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList taskListLimit;
        taskListLimit = new ArrayTaskList();
        for (Task k:this.arrayList) {
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

    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "arrayList=" + Arrays.toString(arrayList) +
                "} " + super.toString();
    }
}
