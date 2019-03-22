package com.stackroute.services;

import com.stackroute.domain.PendingTasks;
import com.stackroute.domain.Task;
import com.stackroute.repository.SettlementRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

public class SettlementServicesTest {
    @Mock
    SettlementRepository settlementRepository;
    @InjectMocks
    SettlementServicesImpl settlementServices;

    PendingTasks pendingTasks;
    Task task;
    List<Task> taskList;
    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);
        pendingTasks = new PendingTasks();
        pendingTasks.setTaskList(new ArrayList<>());
        task = new Task();
        taskList = new ArrayList<>();
    }
    @Test
    public void testPutPendingTasks() {
        doReturn(pendingTasks).when(settlementRepository).save(pendingTasks);
        assertEquals(settlementServices.putPendingTasks(pendingTasks), pendingTasks);
    }
    @Test
    public void testAppendPendingTasks() {
        doNothing().when(settlementRepository).deleteById(pendingTasks.getPendingTasksId());
        doReturn(pendingTasks).when(settlementRepository).save(pendingTasks);
        doReturn(pendingTasks).when(settlementRepository).findByPendingTasksId(pendingTasks.getPendingTasksId());
        List<Task> taskList = pendingTasks.getTaskList();
        taskList.add(task);
        pendingTasks.setTaskList(taskList);
        assertEquals(pendingTasks, settlementServices.appendTask(pendingTasks.getPendingTasksId(), task));
    }
    @Test
    public void testGetAllPendingTasksForInsured() {
        pendingTasks.setInsurerName("abc");
        pendingTasks.setInsuredName("lfg");
        doReturn(pendingTasks).when(settlementRepository).findAllByInsurerNameAndInsuredName("abc", "lfg");
        assertEquals(pendingTasks, settlementServices.getAllPendingTasksForInsured("abc", "lfg"));
    }
    @Test
    public void testModifyPendingTaskStatus() {
        task.setTaskStatus(false);
        task.setTaskName("abc");
        pendingTasks.setTaskList(taskList);
        doReturn(pendingTasks).when(settlementRepository).findByPendingTasksId(pendingTasks.getPendingTasksId());
        doNothing().when(settlementRepository).deleteById(pendingTasks.getPendingTasksId());
        assertEquals(pendingTasks, settlementServices.changePendingTaskStatus(pendingTasks.getPendingTasksId(), task.getTaskName(), true));
    }
    @Test
    public void testGetPendingTaskByPortingRequestId() {
        doReturn(pendingTasks).when(settlementRepository).findByPortingRequestId(pendingTasks.getPortingRequestId());
        List<PendingTasks> pendingTasksList = new ArrayList<>();
        pendingTasksList.add(pendingTasks);
        assertEquals(pendingTasksList, settlementServices.getPendingTasksByPortingRequestId(pendingTasks.getPortingRequestId()));
    }
}