package com.stackroute.pie.repository;

import com.stackroute.pie.domain.PortingRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PortingRequestRepository extends MongoRepository<PortingRequest,Integer> {
    public Boolean existsByPortingRequestId( int requestId);
    public Optional<PortingRequest> findByPortingRequestId(int requestId);
    public void deleteByPortingRequestId(int requestId);
    public Boolean existsByInsuredName(String insuredName);
    public Boolean existsByInsurerName(String insurerName);
    public Boolean existsByNewInsurerName(String newInsurerName);
    public List<PortingRequest> findByInsuredName(String insuredName);
    Optional<List<PortingRequest>> findByInsurerName(String insurerName);
    Optional<List<PortingRequest>> findByNewInsurerName(String newInsurerName);
    Optional<PortingRequest> findByInsuredNameAndCreateDate (String insuredName,Date createDate);
    void deleteByInsuredNameAndCreateDate(String insuredName,Date createDate);

    @Query
    PortingRequest findTopByOrderByPortingRequestIdDesc();
}
