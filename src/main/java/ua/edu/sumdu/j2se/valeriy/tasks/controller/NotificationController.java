package ua.edu.sumdu.j2se.valeriy.tasks.controller;

import ua.edu.sumdu.j2se.valeriy.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.valeriy.tasks.model.Tasks;
import ua.edu.sumdu.j2se.valeriy.tasks.view.ShowNotification;

import java.time.LocalDateTime;

public class NotificationController extends Thread {
    private AbstractTaskList taskList;
    private ShowNotification showNot;

    public NotificationController(AbstractTaskList taskList){
        //this.showNot = showNot;
        this.taskList = taskList;
    }
    private void notificationTasks(){
        //Уведомление за 15 минут

        AbstractTaskList notTasks = (AbstractTaskList) Tasks.incoming(taskList,LocalDateTime.now(),LocalDateTime.now().plusMinutes(15));
        if(notTasks.size() > 1) {
            showNot = new ShowNotification();
            showNot.show(notTasks);
        }
    }
    //Уведомление через каждых 30 секунд
    @Override
    public void run() {
            for(;;){
                notificationTasks();
                try {
                    sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
}



