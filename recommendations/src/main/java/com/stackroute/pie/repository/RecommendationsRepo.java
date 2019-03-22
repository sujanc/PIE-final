package com.stackroute.pie.repository;

import com.stackroute.pie.domain.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface RecommendationsRepo extends Neo4jRepository<Recommendation,Long> {

    @Query("CREATE (insurer:Insurer{insurerLicense:{insurerLicense},insurerName:{insurerName}})")
    Insurer newInsurer(@Param("insurerId")Long insurerId, @Param("insurerName")String insurerName, @Param("insurerLicense")String insurerLicense);

    @Query("CREATE (policy:Policy{maxAge:{maxAge},minAge:{minAge},uniqueId:{uniqueId},policyId:{policyId},policyName:{policyName},insurerName:{insurerName},insurerLicense:{insurerLicense},genderAvail:{genderAvail},policyDescription:{policyDescription},diseasesCovered:[value in {diseasesCovered} | toString(value)],policyType: {policyType},imageUrl:{imageUrl}})")
    Policy newPolicy(@Param("uniqueId")String uniqueId,@Param("policyId")Long policyId, @Param("insurerName")String insurerName,@Param("insurerLicense")String insurerLicense, @Param("policyName")String policyName, @Param("minAge")int minAge, @Param("maxAge")int maxAge, @Param("genderAvail")String genderAvail, @Param("diseasesCovered")List<String> diseasesCovered, @Param("policyType")String policyType,@Param("policyDescription")String policyDescription,@Param("imageUrl") String imageUrl);

    @Query("CREATE (insured:Insured{insuredId:{insuredId},username:{username},gender:{gender},age:{age},existingDisease:{existingDisease},numberOfDependants:{numberOfDependants}})")
    Insured newInsured(@Param("insuredId")int insuredId,@Param("username")String username, @Param("gender")String gender,@Param("age")Long age,@Param("existingDisease")String existingDisease,@Param("numberOfDependants")int numberOfDependants );

    @Query("CREATE (familyMembers:FamilyMembers{username:{username},memberName:{memberName},memberAge:{memberAge},relation:{relation},memberGender:{memberGender}})")
    FamilyMembers newFamilyMembers(@Param("username")String username,@Param("memberName")String memberName,@Param("memberAge")Long memberAge,@Param("relation")String relation,@Param("memberGender")String memberGender);

    @Query("MATCH (n:Insurer{insurerLicense:{insurerLicense}}) SET n.insurerName={insurerName}")
    Insurer updateInsurer (@Param("insurerId")Long insurerId, @Param("insurerName")String insurerName, @Param("insurerLicense")String insurerLicense);

    @Query("MATCH (n:Policy{policyId:{policyId}}) SET n.maxAge={maxAge} SET n.minAge={minAge} SET n.policyName={policyName} SET n.insurerName={insurerName} SET n.gender={gender} SET n.diseases=[value in {diseases} | toString(value)] SET n.policyType={policyType}")
    Policy updatePolicy(@Param("policyId")Long policyId, @Param("insurerName")String insurerName, @Param("policyName")String policyName, @Param("minAge")int minAge, @Param("maxAge")int maxAge, @Param("gender")String gender, @Param("diseases")List<String> diseases, @Param("policyType")String policyType);

    @Query("MATCH (n:Insured{insuredId})")
    Insured updateInsured(@Param("insuredId")int insuredId,@Param("username")String username, @Param("gender")String gender,@Param("age")Long age,@Param("existingDisease")String existingDisease,@Param("numberOfDependants")int numberOfDependants );

    @Query("MATCH (n:Insurer{insurerLicense:{insurerLicense}}) DELETE n")
    Insurer deleteInsurer(@Param("insurerLicense")String insurerLicense);

    @Query("MATCH (p:Policy{policyId:{policyId}}) DETACH DELETE p")
    Policy deletePolicy(@Param("policyId")Long policyId);

    @Query("MATCH (u:Insured{userId:{userId}}) DELETE u")
    Insured deleteInsured(@Param("insuredId")int insuredId);



    @Query("MATCH (policy:Policy) WHERE NOT ((policy)-[:IS_POLICY_OF]->()) WITH (policy) MATCH(insurer:Insurer) WHERE policy.insurerName = insurer.insurerName CREATE (policy)-[:IS_POLICY_OF]->(insurer)")
    void insurerPolicy(@Param("insurerName") String insurerName,@Param("policyId") Long policyId);

    @Query("MATCH (policy:Policy{policyId:{policyId}}),(insured:Insured{username:{username}}) CREATE (insured)-[:HAS_A_POLICY_IN]->(policy)")
    void insuredPolicy(@Param("policyId") Long policyId,@Param("username")String username);

    @Query("MATCH (familyMembers:FamilyMembers) WHERE NOT ((familyMembers)-[:DEPENDANTS_OF]->()) WITH (familyMembers) MATCH(insured:Insured) WHERE familyMembers.username=insured.username MATCH(insured:Insured) CREATE (familyMembers)-[:DEPENDANTS_OF]->(insured)")
    void addDependant(@Param("memberName")String memberName,@Param("username")String username);

    @Query("MATCH (policy:Policy{policyId:{policyId}}),(insured:Insured{username:{username}}) CREATE (insured)-[:VIEWED_POLICY]->(policy)")
    void viewedPolicy(@Param("policyId") Long policyId,@Param("username")String username);

    @Query("MATCH p= (insured)-[r:VIEWED_POLICY]->(policy) RETURN policy LIMIT 5")
    List<Policy> findViewedPolicy();

    @Query("MATCH p= (familyMembers)-[r:DEPENDANTS_OF]->(insured) WHERE familyMembers.username=$username RETURN familyMembers")
    FamilyMembers findDependants(@Param("username")String username);

    @Query("match  p = (insured)-[:HAS_A_POLICY_IN]->(policy) where insured.username starts with $username return policy")
    List<Policy> findByuserName(String username);

    @Query("MATCH  p = (policy:Policy) WHERE (policy.maxAge>$age AND policy.minAge<$age) RETURN policy LIMIT 5")
    List<Policy> findByAge(@Param("age") Long age);


    @Query("MATCH p= (policy:Policy)  where policy.gender=$userGender RETURN policy")
    List<Policy> findByGender(String userGender);

    @Query("MATCH p= (policy:Policy)  where policy.diseases= $existingDisease RETURN policy")
    List<Policy> findByDisease(@Param("existingDisease")List<String> existingDisease);

    @Query("MATCH  p = (policy:Policy) WHERE (policy.maxAge>$age AND policy.minAge<$age) AND (policy.genderAvail=$gender) RETURN policy" )
    List<Policy> findByAgeGender(@Param("age")Long age,@Param("gender")String gender);

    @Query("MATCH  p = (policy:Policy) WHERE (policy.maxAge>$age AND policy.minAge<$age) AND (policy.diseasesCovered=$policyDisease) RETURN policy")
    List<Policy> findByAgeDisease(Integer age,List<String> policyDisease);

    @Query("MATCH  p = (policy:Policy) WHERE (policy.gender=$userGender) AND (policy.diseasesCovered=$policyDisease) RETURN policy")
    List<Policy> findByGenderDisease(String userGender,List<String>policyDisease);

    @Query("MATCH p= (insured:Insured)  where insured.username=$username RETURN insured")
    Insured findUser(@Param("username")String username);

    @Query("MATCH  p = (policy:Policy) WHERE (policy.maxAge>$age AND policy.minAge<$age) AND (policy.gender=$gender) OR (policy.existingDisease=$existingDisease)RETURN policy" )
    List<Policy> findByAgeGenderDisease(@Param("age")Long age,@Param("gender")String gender,@Param("existingDisease")String existingDisease);

}

