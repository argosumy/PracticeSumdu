package ua.edu.sumdu.j2se.valeriy.tasks.view;

import ua.edu.sumdu.j2se.valeriy.tasks.model.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ActionTask implements Action {

    public ActionTask(AbstractTaskList taskList) {
    }
    /**Добавляет задачу в список с последующим
     *сохранением списка задач в файл с учетом добавленного
    * @param taskList текущий список задач
    *
    */
    @Override
    public void taskAdd(AbstractTaskList taskList) {
        Task newTask = null;
        String title;
        LocalDateTime [] startEnd = {null,null};
        System.out.print("Введите название задачи - ");
        title = readText();
        startEnd = startEndTask();
        if(startEnd[1] == null) {
            newTask = new Task(title, startEnd[0]);
            taskList.add(newTask);
        }
        else {
            System.out.print("ВВЕДИТЕ интервал повторений - ");
            int interval = readKey();
            newTask = new Task(title,startEnd[0],startEnd[1],interval);
            taskList.add(newTask);
        }
        //сохранение данных
        saveFile(taskList);
    }

    /**
     * Проверяет на корректность данные введенные с клавиатуры
     * @return Возвращает массив из 2-x элементов Start и End
     */
    public LocalDateTime[] startEndTask(){
        LocalDateTime [] startEnd = {null,null};
        for(;;) {
            startEnd[0] = inTaskDataKey(null,false);
            if(startEnd[0].isAfter(LocalDateTime.now())){
                break;
            }
            System.out.println("Задача не может начинаться раньше  "
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy'г.'")));
        }
        System.out.println("Задача повторяющаяся,");
        System.out.println("1. ДА     /     2.НЕТ");
        if (readKey() == 1){
            for(;;) {
                startEnd[1] = inTaskDataKey(startEnd[0], true);
                if(startEnd[0].isBefore(startEnd[1])){
                    break;
                }
                System.out.println("Задача не может окончаться раньше чем заплонирован старт ");
            }
        }
        return startEnd;
    }

    /**
     * Удаление задачи из списка по номеру с последующим сохранением
     * обновленного списка в фаил
     * @param taskList текущий список задач
     */
    @Override
    public void taskRemove(AbstractTaskList taskList) {
        System.out.print("Выберите номр задачи для удаления...");
        taskList.remove(taskList.getTask(readKey()-1));
        //Save file
        saveFile(taskList);
        }

    /**
     *Редактирование задачи: изменение названия, временные характеристики начала, окончания,
     * меняет опцию задачи с активной на пассивную и наоборот. После изменений сохраняет в файл текущий
     * список задач
     * @param taskList текущий список задач
     * @param numAction номер операции: 1. изменение название задачи 2. изменение временных характеристик
     *3.Изменение активности задачи
     */
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
            LocalDateTime [] startEnd = {null,null};
            startEnd = startEndTask();
            if(startEnd[1] == null) {
                task = new Task(task.getTitle(), startEnd[0]);
            }
            else {
                System.out.print("ВВЕДИТЕ интервал повторений - ");
                int interval = readKey();
                task = new Task(task.getTitle(),startEnd[0],startEnd[1],interval);
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
        //Save file
        saveFile(taskList);
    }

    /**
     * Ввод с клавиатуры временного параметра Start задачи если маркер isRepeat равен False.
     * Ввод с клавиатуры параметра End задачи если маркер isRepeat равен True.
     * Временные параметры вводятся целыми числами с последующим преобразованием к типу LocalDateTime
     * @param start Начало выполнеия задачи. Если начало выполнения задачи не задано, то start = NULL
     * @param isRepeat маркер задачи на повторяемость
     * @return dateTime возврат даты
     */
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
            System.out.println( "Старт задачи запланирован " + start.format(DateTimeFormatter.ofPattern("dd MMMM yyyy'г.' HH':'mm")));
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
                    System.out.println("Ошибка " + "ВЫ ВВЕЛИ НЕКОРРЕКТНЫЙ СИМВОЛ !!!");
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

    private boolean erorRead(int min, int max, int value){
        if((value < min) || (value > max)) {
            System.out.println("Не корректный ввод");
            return false;
        }
        return true;
    }

    /**
     * Ввод назавния задачи с клавиатуры с проверкой на соответствие шаблону
     * @return text возвращает название задачи
     */
    private String readText() {
        String text;
        for(;;){
            while (true) {
                 Scanner in = new Scanner(System.in);
                 text = in.nextLine();
                 break;
                 }
            if(text.matches("^[A-ZА-Я]?[a-zа-я]*+[\\s[A-ZА-Я]?[a-zа-я]*]*")){
                break;
            }
            else {
                System.out.println("Используются не корректные символы");
                System.out.println("ПОВТОРИТЕ ВВОД");
                System.out.println("например 'Свидание с Наташей'");
            }
        }
        return text;
    }

    public int readKey() {
        int num;
        while (true) {
            Scanner in = new Scanner(System.in);
            num = in.nextInt();
            break;
        }
        return num;
    }



    /**
     * Сохраняет текущий список задач в двух файлах (бинарный "tasks.txt" и в формате JSon "tasksjso.txt")
     * @param taskList текущий список задач
     */
    private void saveFile (AbstractTaskList taskList){
        File taskFile = new File("src/main/resources/tasks.txt");
        File taskFileJson = new File("src/main/resources/tasksjson.txt");
        try {
            taskFile.delete();
            taskFile.createNewFile();
            taskFileJson.delete();
            taskFileJson.createNewFile();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        TaskIO.writeBinary(taskList,taskFile);
        TaskIO.writeText(taskList,taskFileJson);
    }

}