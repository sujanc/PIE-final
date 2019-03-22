package com.stackroute.services;

import com.stackroute.repository.SettlementRepository;
import com.stackroute.domain.PendingTasks;
import com.stackroute.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SettlementServicesImpl implements SettlementServices {
    private SettlementRepository settlementRepository;
    @Autowired
    public SettlementServicesImpl(SettlementRepository settlementRepository) {this.settlementRepository = settlementRepository;}
    @Override
    public List<PendingTasks> getAllPendingTasksForInsurer(String insurerName) {
        return settlementRepository.findAllByInsurerNameOrderByInsuredNameAsc(insurerName);
    }
    @Override
    public PendingTasks appendTask(int pendingTasksId, Task task) {
        PendingTasks pendingTasks = settlementRepository.findByPendingTasksId(pendingTasksId);
        List<Task> currentListOfTasks = pendingTasks.getTaskList();
        if(currentListOfTasks == null) {
            List<Task> newTaskList = new ArrayList<>();
            newTaskList.add(task);
            pendingTasks.setTaskList(newTaskList);
            settlementRepository.deleteById(pendingTasksId);
            settlementRepository.save(pendingTasks);
            return pendingTasks;
        }
        currentListOfTasks.add(task);
        pendingTasks.setTaskList(currentListOfTasks);
        settlementRepository.deleteById(pendingTasksId);
        settlementRepository.save(pendingTasks);
        return pendingTasks;
    }


    @Override
    public PendingTasks modifyTask(int pendingTasksId, Task oldTask, Task newTask) {
        PendingTasks pendingTasks = settlementRepository.findByPendingTasksId(pendingTasksId);
        settlementRepository.deleteById(pendingTasksId);
        List<Task> currentListOfTasks = pendingTasks.getTaskList();
        for(Task task : currentListOfTasks) {
            if(task.equals(oldTask)) {
                break;
            }
        }
        pendingTasks.setTaskList(currentListOfTasks);
        settlementRepository.save(pendingTasks);
        return pendingTasks;
    }


    @Override
    public PendingTasks getAllPendingTasksForInsured(String insurerName, String insuredName) {
        return settlementRepository.findAllByInsurerNameAndInsuredName(insurerName, insuredName);
    }

    @Override
    public PendingTasks putPendingTasks(PendingTasks pendingTasks) {
        PendingTasks pendingTasks1= settlementRepository.findTopByOrderByPendingTasksIdDesc();
        if(pendingTasks1 != null)
            pendingTasks.setPendingTasksId(pendingTasks1.getPendingTasksId() + 1);
        else
            pendingTasks.setPendingTasksId(0);
        settlementRepository.save(pendingTasks);
        return pendingTasks;
    }

    @Override
    public PendingTasks deleteTask(int pendingTasksId, String taskName) {
        PendingTasks pendingTasks = settlementRepository.findByPendingTasksId(pendingTasksId);
        settlementRepository.deleteById(pendingTasksId);
        List<Task> newTaskList = new ArrayList<>();
        List<Task> currentTaskList = pendingTasks.getTaskList();
        for(Task task: currentTaskList) {
            if(task.getTaskName().equals(taskName)) {
                continue;
            }
            newTaskList.add(task);
        }
        pendingTasks.setTaskList(newTaskList);
        settlementRepository.save(pendingTasks);
        return pendingTasks;
    }

    @Override
    public PendingTasks changePendingTaskStatus(int pendingTasksId, String taskName, boolean status) {
        PendingTasks pendingTasks = settlementRepository.findByPendingTasksId(pendingTasksId);
        List<Task> taskList = pendingTasks.getTaskList();
        for(Task task: taskList) {
            if(task.getTaskName() != null && task.getTaskName().equals(taskName)) {
                task.setTaskStatus(status);
                break;
            }
        }
        pendingTasks.setTaskList(taskList);
        settlementRepository.deleteById(pendingTasksId);
        settlementRepository.save(pendingTasks);
        return pendingTasks;
    }

    @Override
    public List<PendingTasks> getPendingTasksByPortingRequestId(int portingRequestId) {
        List<PendingTasks> pendingTasks = new ArrayList<>();
        pendingTasks.add(settlementRepository.findByPortingRequestId(portingRequestId));
        if(pendingTasks.get(0) == null) {
            pendingTasks.clear();
            PendingTasks pendingTasks1 = new PendingTasks();
            pendingTasks1.setPortingRequestId(portingRequestId);
            pendingTasks1.setTaskList(new ArrayList<>());
            PendingTasks pendingTasks2= settlementRepository.findTopByOrderByPendingTasksIdDesc();
            if(pendingTasks2 != null)
                pendingTasks1.setPendingTasksId(pendingTasks2.getPendingTasksId() + 1);
            else
                pendingTasks1.setPendingTasksId(0);
            settlementRepository.save(pendingTasks1);
            pendingTasks.add(pendingTasks1);
        }
        return pendingTasks;
    }

}
