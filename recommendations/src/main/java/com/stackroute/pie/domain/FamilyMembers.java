package com.stackroute.pie.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyMembers {
    @Relationship(type="DEPENDANTS_OF")
    @Id
    private String username;
    private  String memberName;
    private Long memberAge;
    private String relation;
    private String memberGender;
}
