package com.stackroute.pie.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="familymembers")

public class FamilyMembers {

    private  String username;
    private  String memberName;
    private long memberAge;
    private String relation;
    private String memberGender;

}
