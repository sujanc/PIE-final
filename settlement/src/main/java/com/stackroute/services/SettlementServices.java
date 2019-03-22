package com.stackroute.services;

import com.stackroute.domain.PendingTasks;
import com.stackroute.domain.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SettlementServices {
    List<PendingTasks> getAllPendingTasksForInsurer(String insurerName);
    PendingTasks appendTask(int pendingTasksId, Task task);
    PendingTasks modifyTask(int pendingTasksId, Task oldTask, Task newTask);
    PendingTasks getAllPendingTasksForInsured(String insurerName, String insuredName);
    PendingTasks putPendingTasks(PendingTasks pendingTasks);
    PendingTasks deleteTask(int pendingTasksId, String taskName);
    PendingTasks changePendingTaskStatus(int pendingTasksId, String taskName, boolean status);
    List<PendingTasks> getPendingTasksByPortingRequestId(int portingRequestId);
}
