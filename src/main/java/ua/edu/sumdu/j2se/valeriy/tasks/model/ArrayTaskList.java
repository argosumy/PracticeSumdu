package ua.edu.sumdu.j2se.valeriy.tasks.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;


import static java.util.Arrays.stream;

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
    public boolean equals(Object o) {
        if (this.hashCode() == o.hashCode()) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
       // ArrayTaskList taskList = (ArrayTaskList) o;
        return false;//Arrays.equals(arrayList, taskList.arrayList);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        LinkedTaskList arrayListLinked = new LinkedTaskList();
        for(Task k: this.arrayList){
            arrayListLinked.add(k);
        }
        return Objects.hash(arrayListLinked.getActualNode().getTask());
    }

    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "arrayList=" + Arrays.toString(arrayList) +
                "} " + super.toString();
    }


    @Override
    public Iterator <Task> iterator() {
        return new TaskIterator();
    }
    public class TaskIterator implements Iterator {
            boolean marker;
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return ((currentIndex < size()) && (arrayList[currentIndex] != null));
            }

            @Override
            public Task next() {
                if (hasNext()) {
                    marker = true;
                    return arrayList[currentIndex++];
                }
                return null;
            }

            @Override
            public void remove() throws IllegalStateException {
                if(marker){

                    arrayListNew = new Task[arrayList.length];
                    int j = 0;
                    for (int i = 0; i < arrayList.length; i++) {
                        if ((currentIndex-1) == i) {                        //arrayListNew[i] = arrayList[j];
                            currentIndex--;
                            continue;
                        }
                        arrayListNew[j] = arrayList[i];
                        j++;
                    }
                    arrayList = arrayListNew;
                }
                else throw new IllegalStateException();
            }
    }


    @Override
    public Stream<Task> getStream() {
        Stream<Task> stream = Stream.of(arrayList);
        return stream;
    }
}
