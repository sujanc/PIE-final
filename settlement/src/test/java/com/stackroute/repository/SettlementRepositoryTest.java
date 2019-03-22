//package com.stackroute.repository;
//
//import com.mongodb.client.MongoClient;
//import com.stackroute.domain.PendingTasks;
//import junit.runner.Version;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import com.stackroute.repository.SettlementRepository;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//@DataMongoTest
//@RunWith(SpringRunner.class)
//public class SettlementRepositoryTest {
//
//    @Autowired
//    SettlementRepository settlementRepository;
//    PendingTasks pendingTasks;
//    @Before
//    public void setUp() throws Exception {
//        pendingTasks = new PendingTasks();
//        String ip = "localhost";
//        int port = 27017;
//
//        IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION)
//                .net(new Net(ip, port, Network.localhostIsIPv6()))
//                .build();
//
//        MongodStarter starter = MongodStarter.getDefaultInstance();
//        mongodExecutable = starter.prepare(mongodConfig);
//        mongodExecutable.start();
//        mongoTemplate = new MongoTemplate(new MongoClient(ip, port), "test");
//
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        settlementRepository.deleteAll();
//    }
//    @Test
//    public void testFindById() {
//        for(int i = 0; i < 256; i++) {
//            pendingTasks.setPendingTasksId(i);
//            settlementRepository.save(pendingTasks);
//            assertEquals(pendingTasks.getPendingTasksId(), settlementRepository.findByPendingTasksId(i).getPendingTasksId());
//        }
//    }
//    @Test
//    public void testFindByPortingRequestId() {
//        for(int i = 0; i < 256; i++) {
//            pendingTasks.setPortingRequestId(i);
//            pendingTasks.setPendingTasksId(i);
//            settlementRepository.save(pendingTasks);
//            assertEquals(pendingTasks.getPortingRequestId(), settlementRepository.findByPortingRequestId(i).getPortingRequestId());
//        }
//    }
//    @Test
//    public void testFindAllByInsurerNameAndInsuredName() {
//        pendingTasks.setInsuredName("abc");
//        pendingTasks.setInsurerName("lfg");
//        pendingTasks.setPendingTasksId(1);
//        settlementRepository.save(pendingTasks);
//        assertEquals(pendingTasks.getPendingTasksId(), settlementRepository.findAllByInsurerNameAndInsuredName("lfg", "abc").getPendingTasksId());
//    }
//    @Test
//    public void testFindAllByInsurerNameOrderByInsuredNameAsc() {
//        pendingTasks.setPendingTasksId(0);
//        pendingTasks.setInsuredName("abc");
//        pendingTasks.setInsurerName("lfg");
//        settlementRepository.save(pendingTasks);
//    }
//}