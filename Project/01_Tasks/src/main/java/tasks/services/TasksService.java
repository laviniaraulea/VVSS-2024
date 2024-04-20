package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tasks.model.TasksRepository;
import tasks.model.Task;
import tasks.model.TasksOperations;

import java.util.Date;


public class TasksService {

    private TasksRepository tasks;


    public void setTasks(TasksRepository tasks) {
        this.tasks = tasks;
    }

    public TasksService(TasksRepository tasks){
        this.tasks = tasks;
    }

    public void removeTask(Task task){
        tasks.remove(task);
    }
    public void saveTask( Task task){

        tasks.add(task);
    }
    public void saveTask(ObservableList tasksList, String newTitle, Date newEndDate,  Date newStartDate, int newInterval){
        if(newTitle.equals("")){ throw new IllegalArgumentException("The title should not be empty");}
        if (newStartDate.after(newEndDate)){ throw new IllegalArgumentException("Start date should be before end");}
        Task task = new Task(newTitle, newStartDate,newEndDate, newInterval);
        tasksList.add(task);
        tasks.add(task);
    }

    public ObservableList<Task> getObservableList(){
        return FXCollections.observableArrayList(tasks.getAll());
    }
    public String getIntervalInHours(Task task){
        int seconds = task.getRepeatInterval();
        int minutes = seconds / DateService.SECONDS_IN_MINUTE;
        int hours = minutes / DateService.MINUTES_IN_HOUR;
        minutes = minutes % DateService.MINUTES_IN_HOUR;
        return formTimeUnit(hours) + ":" + formTimeUnit(minutes);//hh:MM
    }
    public String formTimeUnit(int timeUnit){
        StringBuilder sb = new StringBuilder();
        if (timeUnit < 10) sb.append("0");
        if (timeUnit == 0) sb.append("0");
        else {
            sb.append(timeUnit);
        }
        return sb.toString();
    }

    public int parseFromStringToSeconds(String stringTime){//hh:MM
        String[] units = stringTime.split(":");
        int hours = Integer.parseInt(units[0]);
        int minutes = Integer.parseInt(units[1]);
        int result = (hours * DateService.MINUTES_IN_HOUR + minutes) * DateService.SECONDS_IN_MINUTE;
        return result;
    }

    public Iterable<Task> filterTasks(Date start, Date end){
        TasksOperations tasksOps = new TasksOperations(getObservableList());
        Iterable<Task> filtered = tasksOps.incoming(start,end);
        //Iterable<Task> filtered = tasks.incoming(start, end);

        return filtered;
    }
}
