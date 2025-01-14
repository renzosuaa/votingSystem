
package com.mycompany.votingsystem;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author admin
 */
public final class votingAnalytics implements sqlInfo{
    
    private final Graph graph;
    int totalPresidentVotes = 0;
    int totalVicePresidentVotes = 0;
    int totalSenatorVotes = 0;
    private final Voter voter = new Voter();
    private final Candidate candidate = new Candidate();
    private final quickSort qSort = new quickSort();
    
    votingAnalytics() {
       
        graph = new Graph();

        //add all the candidates to the graph        
        for (Integer candidates : candidate.listOfAllCandidates("President")){
        graph.add(-5, candidates);
        }
        
        for (Integer candidates : candidate.listOfAllCandidates("Vice President")){
        graph.add(-5, candidates);
        }

        for (Integer candidates : candidate.listOfAllCandidates("Senator")){
        graph.add(-5, candidates);
        }      
        addAllVoter();        

    }
    
    // returns an array of Integers containing the number of voters of a Candidate depending on certain demographics
    // It is in the order of : [no.male, no.female, on age of 18-33, on age 33-47, on age 48-63, 64+]  
    int[] getCandidateStatistics(int candidateID){
        int male = 0, female = 0, gen1 =0, gen2 =0, gen3=0, gen4 =0;
        
        for (Node node :graph.graph.get(candidateID).vertex){
            
            switch (getGeneration(voter.getBirthday(node.ID))){
                    case "Generation 1" -> gen1++;
                    case "Generation 2" -> gen2++;
                    case "Generation 3" -> gen3++;
                    case "Generation 4" -> gen4++;
                }
            if (voter.getGender(node.ID).equals("Male")){
                male++;    
            } else {
                female++;    
            }
        }
        int totalCandidateVotes = male+female;
        int[] candidateStats = {male,female,gen1,gen2,gen3,gen4,totalCandidateVotes};
        return candidateStats;
    }
    
//    Function that sorts the Hashmap of candidates' votes
    HashMap<Integer,Integer> sortedCandidatesByVote(String Position){
        HashMap<Integer,Integer> hmCandidates = new HashMap<>();
        for (int candidateIDs : candidate.listOfAllCandidates(Position)){
            hmCandidates.put( candidateIDs,graph.graph.get(candidateIDs).vertex.size()); 
        }
        
        HashMap <Integer, Integer> sortedCandidates = qSort.sortByV(hmCandidates);     
        return sortedCandidates;
    }
    
        //Function that returns the generation range of a candidate
    private String getGeneration (String birthday){
        
        int age,birthYear,currYear;
        LocalDate ldtBirthday = LocalDate.parse(birthday,DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        birthYear = ldtBirthday.getYear();
        currYear = LocalDate.now().getYear();
        age = currYear-birthYear;
        
        if(age>=18&&age<34){
            return "Generation 1";
        }
        else if(age >=34&&age <48){
           return "Generation 2";
        }
        else if(age >=48&&age <64){
           return "Generation 3";
        }
        else{
           return "Generation 4";
        }
    }
    
        /*Add all the voterID of voters to their respective candidates in the graph
            while counting the numbers of voters voted in different positions*/
    void addAllVoter() {
        try {      
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement ps = con.prepareStatement("Select voterID,candidateID from dbvotingsystem.vote");
            ResultSet rs = ps.executeQuery();                    
            while (rs.next()){
                int voterID = rs.getInt(1), candidateID = rs.getInt(2);
                switch (new Candidate(candidateID).position) {
                    case "President":
                        totalPresidentVotes++;
                        break;
                    case "Vice President":
                        totalVicePresidentVotes++;
                        break;
                    case "Senator":
                        totalSenatorVotes++;
                        break;
                }
                graph.add(candidateID, voterID);
            }  
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        }          
    }
    
        //Method that returns the total counted votes per position
    int votePerPosition(String position){
        switch (position) {
            case "President":
                return totalPresidentVotes;
            case "Vice President":
                return totalVicePresidentVotes;
            case "Senator":
                return totalSenatorVotes;
            default:
                break;
        }
        return 0;
    }  
}
