package ua.edu.sumdu.j2se.valeriy.tasks;

import java.util.Arrays;
import java.util.ArrayList;

public class ArrayTaskList {
    public Task []  arrayList;
    private Task [] arrayListNew;


    public ArrayTaskList() {
        arrayList = new Task[0];
    }

    public ArrayTaskList(int size){

        arrayListNew = new  Task[size];
    }

    public void add(Task task) {
        if (size() == 0) {
            arrayListNew = new Task[1];
            this.arrayList = arrayListNew;
            arrayList [0] = task;
            //marker = true;
        }
        else {
            if (arrayList.length == size()) {
                arrayListNew = new Task [size() + 1];
            }
            //Task [] arrayListNew = new Task[arrayList.length + 1];
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

    public boolean remove(Task task) {
        boolean removTask = false; //проверка удаления
        //создаем промежуточный массив
        arrayListNew = new Task [arrayList.length];
        int j = 0;
        for (Task k: arrayList) {
            if (( k != task ) | (removTask == true)) {
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

    public int size() {
        int i = 0;
        for (Task k:arrayList) {
            if (k != null){
                i++;
            }
        }
        return i;
    }

    public Task getTask(int index) {
         return arrayList[index];
    }

    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList arrayListPredel = new ArrayTaskList(size());
        for (Task k: arrayList) {
            if (k.isActive() != false) {
                arrayListPredel.arrayList[0] = k;
            }
        }
        return arrayListPredel;
    }

    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "arrayList=" + Arrays.toString(arrayList) +
                "} " + super.toString();
    }
}
