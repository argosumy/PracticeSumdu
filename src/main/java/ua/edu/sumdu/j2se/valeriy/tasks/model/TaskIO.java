package ua.edu.sumdu.j2se.valeriy.tasks.model;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Iterator;

public class TaskIO {
    static ZoneId zoneId = ZoneId.systemDefault();

    private static final Logger logger = Logger.getLogger(TaskIO.class );

    public static void write(AbstractTaskList tasks, OutputStream out) {
        Task task = null;
        Iterator it = tasks.iterator();
            try (DataOutputStream dataOutput = new DataOutputStream(out)) {
                dataOutput.write(tasks.size());
                System.out.println("Сохранение задач: " + tasks.size() + " шт. ");
                while (it.hasNext()) {
                    task = (Task) it.next();
                    System.out.println("Задача сохранена: - " + task);
                    dataOutput.write(task.getTitle().length());
                    dataOutput.writeUTF(task.getTitle());
                    if (task.isActive()) {
                        dataOutput.write(1);
                    } else {
                        dataOutput.write(0);
                    }
                    dataOutput.write(task.getRepeatInterval());
                    if (task.isRepeated()) {
                        dataOutput.writeLong(task.getStartTime().atZone(zoneId).toInstant().toEpochMilli());
                        dataOutput.writeLong(task.getEndTime().atZone(zoneId).toInstant().toEpochMilli());
                    } else {
                        dataOutput.writeLong(task.getTime().atZone(zoneId).toInstant().toEpochMilli());
                        dataOutput.writeLong(task.getRepeatInterval());
                    }
                }
                logger.info("DataOutput Write TRUE");
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
                logger.error("Exeption " + e);
            }
    }

    public static void read(AbstractTaskList tasks, InputStream in){
            try (DataInputStream dataIn = new DataInputStream(in)) {
                int size = dataIn.read();
                System.out.println("У вас " +  size + " задач");
                for (int i = 0; i < size; i++){
                    int titlLength = dataIn.read();
                    String nameTitle = dataIn.readUTF();
                    int activ = dataIn.read();
                    int interval = dataIn.read();
                    LocalDateTime startTime =
                            Instant.ofEpochMilli(dataIn.readLong()).atZone(zoneId).toLocalDateTime();
                    LocalDateTime endTime =
                            Instant.ofEpochMilli(dataIn.readLong()).atZone(zoneId).toLocalDateTime();
                    if(interval > 0) {
                        Task task = new Task(nameTitle, startTime, endTime, interval);
                        task.setRepeated(true);
                        if(activ == 1) task.setActive(true);
                        else task.setActive(false);
                        tasks.add(task);
                    }
                    else {
                        Task task = new Task(nameTitle,startTime);
                        task.setRepeated(false);
                        if(activ == 1) task.setActive(true);
                        else task.setActive(false);
                        tasks.add(task);
                    }
                } //конец цикла for
                logger.info("DataInput Read TRUE");
            } catch (IOException e) {
               // System.out.println("Error read " + e.getMessage());
                logger.error(e);
        }
    }

    synchronized public static void writeBinary(AbstractTaskList tasks, File file) {
        try (FileOutputStream fileOut = new FileOutputStream(file)){
            TaskIO.write(tasks,  fileOut);
            logger.info("Write File TRUE");
        }
        catch (IOException e){
            logger.error(e);
        }

    }

    synchronized public static void readBinary(AbstractTaskList tasks, File file)  {
        try (FileInputStream fileIn = new FileInputStream(file) ){
            TaskIO.read(tasks, fileIn);
            logger.info("Read File TRUE");
        }
        catch (IOException e){
            logger.error(e);
        }
    }

    public static void write(AbstractTaskList tasks, Writer out)throws IOException{
        Gson jsonWrRe = new Gson();
        jsonWrRe.toJson(tasks, out);
        out.close();
    }

    public static void read(AbstractTaskList tasks, Reader in)throws IOException{
        Gson jsonWrRe = new Gson();
        AbstractTaskList list = jsonWrRe.fromJson(in,ArrayTaskList.class);
        for (Object k : list){
            tasks.add((Task) k);
        }
        in.close();
    }

    public static void writeText(AbstractTaskList tasks, File file){
        try (FileWriter writer = new FileWriter(file)){
            write(tasks, writer);
        }
        catch (IOException e){
            logger.error(e);
        }
    }

    public static void readText(AbstractTaskList tasks, File file){
        try (FileReader reader = new FileReader(file)){
            read(tasks,reader);}
        catch (IOException e){
            logger.error(e);
        }
    }

}
