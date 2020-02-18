package ua.edu.sumdu.j2se.valeriy.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.valeriy.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.valeriy.tasks.model.Tasks;
import ua.edu.sumdu.j2se.valeriy.tasks.view.ShowNotification;

import java.time.LocalDateTime;

public class NotificationController extends Thread {
    private AbstractTaskList taskList;
    private ShowNotification showNot;
    private static final Logger logger = Logger.getLogger(NotificationController.class );

    public NotificationController(AbstractTaskList taskList){
        this.taskList = taskList;
    }

    /**
     * Делает выборку задач из текущего списка задач, старт которых запланирован в течении ближайших 15 мин.
     * Вызывает метод show(), который выводит выборку в консоль.
     */
    private void notificationTasks(){
        //Уведомление за 15 минут
        AbstractTaskList notTasks = (AbstractTaskList) Tasks.incoming(taskList,LocalDateTime.now(),LocalDateTime.now().plusMinutes(15));
        if(notTasks.size() > 0) {
            showNot = new ShowNotification();
            showNot.show(notTasks);
        }
    }

    /**
     * Метод run вызывает метод notificationTasks() в отдельном потоке каждые 30 сек.
     */
    @Override
    public void run() {
        for(;;){
            notificationTasks();
            logger.info("task verification");
            try {
                sleep(30000);
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }
    }

}



