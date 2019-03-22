package com.stackroute.repository;

import com.stackroute.domain.PendingTasks;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettlementRepository extends MongoRepository<PendingTasks, Integer> {
    @Query
    public List<PendingTasks> findAllByInsurerNameOrderByInsuredNameAsc(String insurerName);

    @Query
    public PendingTasks findAllByInsurerNameAndInsuredName(String insurerName, String insuredName);

    @Query
    public PendingTasks findByPendingTasksId(int pendingTasksId);

    @Query
    public PendingTasks findByPortingRequestId(int portingRequestId);

    @Query
    PendingTasks findTopByOrderByPendingTasksIdDesc();
}
