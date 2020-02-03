package ua.edu.sumdu.j2se.valeriy.tasks.controller;

import ua.edu.sumdu.j2se.valeriy.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.valeriy.tasks.model.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ActionTask implements Action {

    public ActionTask(AbstractTaskList taskList) {
    }

   // создание новой задачи
    @Override
    public void taskAdd(AbstractTaskList taskList) {
        Task newTask = null;
        String title;
        LocalDateTime end = null;
        System.out.print("Введите название задачи - ");
        title = readText();
        LocalDateTime start = inTaskDataKey(null,false);
        System.out.println("Задача повторяющаяся,");
        System.out.println("1. ДА     /     2.НЕТ");
        if (readKey() == 1){
            end = inTaskDataKey(start,true);
            System.out.print("ВВЕДИТЕ интервал повторений - ");
            int interval = readKey();
            newTask = new Task(title,start,end,interval);
        }
        else newTask = new Task(title,start);
        taskList.add(newTask);
    }

    //Удаление задачи
    @Override
    public void taskRemove(AbstractTaskList taskList) {
        System.out.print("Выберите номр задачи для удаления...");
        taskList.remove(taskList.getTask(readKey()-1));
        }
    //Редактирование задачи
    @Override
    public void taskEdit(AbstractTaskList taskList, int numAction) {
        System.out.print("Выберите номр задачи для редактирования...");
        int i = readKey()-1;
        Task task = taskList.getTask(i);
        System.out.println(task);
        taskList.remove(taskList.getTask(i));
        //вызов метода действий
        if(numAction == 1){
            System.out.println("Название задачи текущее - " + task.getTitle());
            System.out.print("Новое название - ");
            task.setTitle(readText());
        }
        //редактирует временные характеристики
        if(numAction == 2){
            LocalDateTime start = null;
            if(task.getRepeatInterval() != 0){
                start = task.getStartTime();
                System.out.println("Начало задачи (текущее) - "
                        + start.format(DateTimeFormatter.ofPattern("dd MMMM yyyy'г.'")));
                start = inTaskDataKey(start,false);

                LocalDateTime end = inTaskDataKey(start, true);
                System.out.print("ВВЕДИТЕ интервал повторений - ");
                int interval = readKey();
                task.setTime(start, end, interval);
            }
            else{
                start = task.getTime();
                System.out.println("Начало задачи - "
                        + start.format(DateTimeFormatter.ofPattern("dd MMMM yyyy'г.'")));
                start = inTaskDataKey(start, false);
                task.setTime(start);
            }
        }
        //делает задачу активной/не активной
        if(numAction == 3){
            if (task.isActive()) {
                System.out.println("Задача была активная - ТЕПЕРЬ НЕТ");
                task.setActive(false);
            }
            else {
                System.out.println("Задача была не активная - ТЕПЕРЬ АКТИВНАЯ");
                task.setActive(true);
            }
        }
        taskList.add(task);
    }


    // Ввод временных параметров задачи с клавиатуры
    private LocalDateTime inTaskDataKey(LocalDateTime start,Boolean isRepeat){
        String status = null;
        int year = 0;
        int month = 0;
        int day = 0;
        int hour = 0;
        int minute = 0;
        LocalDate data = null;
        LocalTime timeTask = null;
        if (!isRepeat) {
            status = "начала";
            System.out.println( "Сейчас  " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy'г.'")));
        }
        else{
            System.out.println( "Старт задачи заплонирован " + start.format(DateTimeFormatter.ofPattern("dd MMMM yyyy'г.' HH':'mm")));
            status = "завершения";
        }
        for(;;) {
            boolean value = true;
            do {
                System.out.print("Bведите ЧИСЛО " + status + " выполнения задачи (от 1 до 31) - ");
                try {
                    day = readKey();
                    value = erorRead(1,31,day);
                }
                catch (InputMismatchException ex){
                    value = false;
                    System.out.println("Ошибка " + ex + "ВЫ ВВЕЛИ НЕКОРРЕКТНЫЙ СИМВОЛ !!!");
                }
            }
            while (!value);
            do {
                System.out.print("Bведите МЕСЯЦ " + status + " задачи (цифры от 1 до 12) - ");
                month = readKey();
                value = erorRead(1,12,month);
            }
            while (!value);
            do {
                System.out.print("Bведите ГОД " + status + " выполнения задачи от - ");
                year = readKey();
                value = erorRead(LocalDate.now().getYear(), LocalDate.now().plusYears(100).getYear(), year);
            }
            while (!value);
            //преобразовываем числовой формат в ДАТУ
            data = LocalDate.of(year, month, day);
            System.out.println("Bведите ВРЕМЯ " + status + "выполнения задачи ");
            do{
            System.out.print("ЧАС - ");
            hour = readKey();
            value = erorRead(0,23,hour);
            }
            while (!value);
            do {
                System.out.print("МИНУТЫ - ");
                minute = readKey();
                value = erorRead(0,59,minute);
            }
            while (!value);
            //Преобразовываем числовой формат во ВРЕМЯ
            timeTask = LocalTime.of(hour, minute);
            System.out.println("Запланированное время " + status + " "
                    + data.format(DateTimeFormatter.ofPattern("dd MMMM yyyy'г.'"))
                    + " ВРЕМЯ - "
                    + timeTask);
            System.out.println("1. СОХРАНИТЬ    /    2. ПОВТОРИТЬ ВВОД  - ");
            if (readKey() == 1) {
                break;
            }
        }
        LocalDateTime dateTime = LocalDateTime.of(data, timeTask);
        return dateTime;
    }

    public boolean erorRead(int min, int max, int value){
        if((value < min) || (value > max)) {
            System.out.println("Не корректный ввод");
            return false;
        }
        return true;
    }

    public String readText() {
        String text;
        while (true) {
            Scanner in = new Scanner(System.in);
            text = in.nextLine();
            break;
        }
        return text;
    }

    @Override
    public int readKey() {
        int num;
        while (true) {
            Scanner in = new Scanner(System.in);
            num = in.nextInt();
            break;
        }
        return num;
    }

}