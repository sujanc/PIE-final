package com.stackroute.pie.service;
import com.stackroute.pie.domain.BuyPolicy;
import com.stackroute.pie.domain.InsurerPolicy;


import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

 public interface ExternalDbService {

    List<InsurerPolicy> getPolicies(String insurerName) throws ClassNotFoundException, SQLException, UnsupportedEncodingException;
    BuyPolicy buyPolicy(BuyPolicy buyPolicy) throws ClassNotFoundException, SQLException, UnsupportedEncodingException;

}
