package sumdu.netcracker.ua.sumdu.j2se.valeriy.tasks;

public class Task {
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;
    private boolean repeated;

    @Override
    public String toString() {
        return "Task{"
                + "Название задачи='"
                + title
                + '\''
                + ", time="
                + time
                + ", start="
                + start
                + ", end="
                + end
                + ", Повтор задачи через="
                + interval
                + ", Задача активная? = "
                + active
                + ", Задача повторяющаяся? = "
                + repeated
                + '}';
    }

    public Task(String title, int time) {
        this.title = title;
        this.time = time;
        setActive(false);
        repeated = false;

    }

    public Task(String title, int start, int end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        setActive(false);
        repeated = true;

    }

    boolean isActive() {
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
    public int getTime() {
        if (isRepeated() == true) {
            time = (start + interval);
        }
        return time;
    }

    public void setTime(int time) {
        if (isRepeated() == true) {
            repeated = false;
        }
        this.time = time;
    }

    //для повторяющихся задач
    public int getStartTime() {
        if (isRepeated() == false) {
            start = time;
        }
        return start;
    }

    public int getRepeatInterval() {
        if (isRepeated() == false) {
            interval = 0;
        }
        return interval;
    }

    void setTime(int start, int end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
        if (isRepeated() == false) {
            repeated = true;
        }
    }

    public int getEndTime() {
        if (isRepeated() == false) {
            end = time;
        }
        return end;
    }

    public boolean isRepeated() {

        return repeated;
    }

    public int nextTimeAfter(int current) {
        int nextTime = -1;
        if (current <= getStartTime()) {
            nextTime = getStartTime();
        } else {
            if (current < getEndTime()) {
                nextTime = getStartTime();
                while ((getRepeatInterval() != 0) && (current >= (nextTime))) {
                    if ( (current == nextTime) | ((nextTime + getRepeatInterval()) > getEndTime()) ) {
                        nextTime = -1;
                        break;
                    }
                    nextTime = (nextTime + getRepeatInterval());
                }
            }
            if (current == getEndTime()) {
                nextTime = getEndTime();
            }
        }
        return nextTime;
    }
}


