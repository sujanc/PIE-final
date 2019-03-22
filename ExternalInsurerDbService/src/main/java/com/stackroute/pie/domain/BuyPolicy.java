package com.stackroute.pie.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="BuyPolicy")
@Data
@NoArgsConstructor
public class BuyPolicy {

    @Id
    private int id;
    private String policyName;
    private long policyId;
    private String insurerName;
    private String username;
    private String email;
}
