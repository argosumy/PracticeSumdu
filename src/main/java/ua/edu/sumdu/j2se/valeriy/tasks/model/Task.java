package ua.edu.sumdu.j2se.valeriy.tasks.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Task implements Cloneable, Serializable  {
    private String title;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;
    private boolean active;
    private boolean repeated;
    private String id;

    @Override
    public String toString() {
        if((start!=null)&(end!=null))
        return "Task{"
                + "Название задачи='"
                + title
                + '\''
               // + ", time= "
               // + time.format(DateTimeFormatter.ofPattern("dd MMMM yyyy'г.' HH':'mm"))
                + ", start= "
                + start.format(DateTimeFormatter.ofPattern("dd MMMM yyyy'г.' HH':'mm"))
                + ", end= "
                + end.format(DateTimeFormatter.ofPattern("dd MMMM yyyy'г.' HH':'mm"))
                + ", Повтор задачи через= "
                + (interval / 60)  + " мин."
                + ", Задача активная? = "
                + active
                + ", Задача повторяющаяся? = "
                + repeated
                + '}';
        else
            return "Task{"
                    + "Название задачи='"
                    + title
                    + '\''
                    + ", time= "
                    + time.format(DateTimeFormatter.ofPattern("dd MMMM yyyy'г.' HH':'mm"))
                    + ", Задача активная? = "
                    + active
                    + ", Задача повторяющаяся? = "
                    + repeated
                    + '}';
    }

    public Task() {
    }

    public Task(String title, LocalDateTime time)throws IllegalArgumentException {
            this.title = title;
            this.time = time;
        if((title == null) && (time.isBefore(LocalDateTime.now().minusMonths(1000)))) {
            throw new IllegalArgumentException("Параметр  не может быть null");
        }
        setActive(false);
        setRepeated(false);

    }
    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) throws IllegalArgumentException  {
        this.title = title;
        try {
            this.start = start;
            this.end = end;
        }
        catch (IllegalArgumentException ex){
            System.out.println("Не верный параметр");
        }

        this.interval = (int)interval;
        if ((title == null)&&(start.isBefore(LocalDateTime.now()))&&(end.isBefore(LocalDateTime.now()))) {
            throw new IllegalArgumentException("Временные характеристики не могут быть меньше 0");
        }
        setActive(false);
        setRepeated(true);

    }
    public void setRepeated(boolean repeated) {

        this.repeated = repeated;
    }
    public boolean isActive() {

        return active;
    }
    public void setActive(boolean active) {

        this.active = active;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    //для одномоментных задач изменение параметров  выполнения
    public LocalDateTime getTime() throws IllegalArgumentException {
        if (isRepeated()) {
            time = start;
        }
        return time;
    }
    public void setTime(LocalDateTime time) throws IllegalArgumentException  {
        if (isRepeated()) {
            setRepeated(false);
        }
        this.time = time;
    }
    //для повторяющихся задач
    public LocalDateTime getStartTime() {
        if (!isRepeated()) {
            start = time;
        }
        return start;
    }
    public int getRepeatInterval() {
        if (!isRepeated()) {
            interval = 0;
        }
        return interval;
    }
    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
        if (!isRepeated()) {
            setRepeated(true);
        }
    }

    public LocalDateTime getEndTime() {
        if (!isRepeated()) {
            end = time;
        }
        return end;
    }

    public boolean isRepeated() {
        return repeated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        LocalDateTime nextTime;
        nextTime = null;
        if (!isActive()) {
            return nextTime;
        }
        if (current.isBefore(getStartTime()))  {
            nextTime = getStartTime();
        } else {
            if (current.isBefore(getEndTime())) {
                nextTime = getStartTime();
                while ((getRepeatInterval() != 0) && ((current.isAfter(nextTime)) || current.isEqual(nextTime))) {
                    if (nextTime.plusSeconds(getRepeatInterval()).isAfter(getEndTime())) {
                        nextTime = null;
                        break;
                    }
                    nextTime = nextTime.plusSeconds(getRepeatInterval());
                }
            }
        }
        return nextTime;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        Task klonTask = (Task) super.clone();
        return klonTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return time == task.time &&
                start == task.start &&
                end == task.end &&
                interval == task.interval &&
                active == task.active &&
                repeated == task.repeated &&
                title.equals(task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, time, start, end, interval, active, repeated);
    }
}


