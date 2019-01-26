package com.example.reza66.tamin6.models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by www.NooR26.com on 1/11/2019.
 */

public class TaskBank {
    private static TaskBank instance;
    private LinkedHashMap<UUID,Task> mTasks;

    private TaskBank(){
        mTasks =new LinkedHashMap<>();
//        for(int i=0;i<15;i++){
//            Task crime=new Task();
//            crime.setTitle("Crime#"+i);
//            crime.setSolved(i % 2==0);
//            mTasks.put(crime.getId(),crime);
//        }
    }

    public static TaskBank getInstance() {
        if (instance==null)
            instance =new TaskBank();
        return instance;
    }
    public List<Task> getTasks() {
        return new ArrayList<>(mTasks.values());
    }

    public List<Task> getDoneTasks(){
        List<Task> doneTasks=new ArrayList<>();

        List<Task> tasks=getTasks();
        for(int i=0; i<tasks.size(); i++)
            if(tasks.get(i).isDone()) {
                doneTasks.add(tasks.get(i));
            }
        return doneTasks;

    }

    public Task getTask(UUID id){
        return mTasks.get(id);
    }
    public int getIndexOfCrime(UUID id){
        List<Task> tasks=getTasks();
        for(int i=0; i<tasks.size(); i++)
            if(tasks.get(i).getId().equals(id))
                return i;
        return -1;
    }
    public void addTask(Task task){
        mTasks.put(task.getId(),task);
    }

    public void deleteTask(Task task){
        mTasks.remove(task.getId(),task);
    }
}
