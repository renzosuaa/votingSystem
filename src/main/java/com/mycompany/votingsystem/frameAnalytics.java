package com.mycompany.votingsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class frameAnalytics extends JFrame implements ActionListener {

    JTabbedPane tab;
    JLabel lblTotalCasted,lblTotalVotes,lblAnalyticsCandidate,lblAnalyticsVotes,
            lblFemaleVotes,lblFemaleVotesPerc,lblMaleVotes,lblMaleVotesPerc,
            lblAgeGroup,lblGeneration1,lblGeneration2,lblGeneration3,lblGeneration4,
            lblGeneration1Perc,lblGeneration2Perc,lblGeneration3Perc,lblGeneration4Perc;
    percentage perCon = new percentage();      
    JButton btnSignOut;
    votingAnalytics analytics = new votingAnalytics();
    int intTotalCandidateVotes, intTotalVotes = analytics.totalPresidentVotes;

    public frameAnalytics(){
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        tab = new JTabbedPane();
        tab.setSize(500, 500);
        add(tab);

        tab.addTab("Presidents", addPage("President", analytics.sortedCandidatesByVote("President")));
        tab.addTab("Vice Presidents", addPage("Vice President", analytics.sortedCandidatesByVote("Vice President")));
        tab.addTab("Senators", addPage("Senator",analytics.sortedCandidatesByVote("Senator")));

        lblTotalCasted = new JLabel("Total Casted Votes:");
        lblTotalCasted.setBounds(535,12,200,30);
        lblTotalCasted.setFont(new Font("Verdana",Font.BOLD, 18));
        add(lblTotalCasted);

        lblTotalVotes = new JLabel(String.valueOf(analytics.totalPresidentVotes),SwingConstants.CENTER);
        lblTotalVotes.setBounds(550,45,200,30);
        lblTotalVotes.setFont(new Font("Verdana",Font.BOLD, 16));
        add(lblTotalVotes);

        JPanel paneAnalytics = new JPanel();
        paneAnalytics.setBounds(505, 100, 275, 400);
        paneAnalytics.setBackground(Color.white);
        paneAnalytics.setBorder(BorderFactory.createBevelBorder(1));
        paneAnalytics.setLayout(null);
        add(paneAnalytics);
    
        lblAnalyticsCandidate = new JLabel("Candidate Name",SwingConstants.CENTER);
        lblAnalyticsCandidate.setBounds(0, 0, 250, 50);
        lblAnalyticsCandidate.setFont(new Font("Arial",Font.PLAIN,16));
        paneAnalytics.add(lblAnalyticsCandidate);
        
        lblAnalyticsVotes = new JLabel("0.0% " + "  -  " + "0 Votes" ,SwingConstants.CENTER);
        lblAnalyticsVotes.setBounds(0, 25, 275, 50);
        lblAnalyticsVotes.setFont(new Font("Arial",Font.PLAIN,16));
        paneAnalytics.add(lblAnalyticsVotes);
    
        lblFemaleVotes = new JLabel("Female Voters: ");
        lblFemaleVotes.setBounds(5, 75, 275, 50);
        paneAnalytics.add(lblFemaleVotes);
    
            
        lblFemaleVotesPerc = new JLabel("0.00%"+"               "+0);
        lblFemaleVotesPerc.setBounds(115, 75, 275, 50);
        paneAnalytics.add(lblFemaleVotesPerc);
        
        lblMaleVotes = new JLabel("Male Voters: ");
        lblMaleVotes.setBounds(5, 125, 275, 50);
        paneAnalytics.add(lblMaleVotes);
    
        lblMaleVotesPerc = new JLabel("0.00%"+"               "+"0");
        lblMaleVotesPerc.setBounds(115, 125, 275, 50);
        paneAnalytics.add(lblMaleVotesPerc);
        
        
        lblAgeGroup = new JLabel("Age Groups: ");
        lblAgeGroup.setBounds(5, 175, 275, 50);
        paneAnalytics.add(lblAgeGroup);
        
        lblGeneration1 = new JLabel("18-33: ");
        lblGeneration1.setBounds(5, 200, 275, 50);
        paneAnalytics.add(lblGeneration1);
        
        lblGeneration1Perc = new JLabel("0.00%"+"               "+0);
        lblGeneration1Perc.setBounds(115, 200, 275, 50);
        paneAnalytics.add(lblGeneration1Perc);
        
        lblGeneration2 = new JLabel("34-48: ");
        lblGeneration2.setBounds(5, 250, 275, 50);
        paneAnalytics.add(lblGeneration2);
        
        lblGeneration2Perc = new JLabel("0.00%"+"               "+0);
        lblGeneration2Perc.setBounds(115, 250, 275, 50);
        paneAnalytics.add(lblGeneration2Perc);
        
        lblGeneration3 = new JLabel("49-63: ");
        lblGeneration3.setBounds(5, 300, 275, 50);
        paneAnalytics.add(lblGeneration3);
        
        lblGeneration3Perc = new JLabel("0.00%"+"               "+0);
        lblGeneration3Perc.setBounds(115, 300, 275, 50);
        paneAnalytics.add(lblGeneration3Perc);
        
        lblGeneration4 = new JLabel("64-80+: ");
        lblGeneration4.setBounds(5, 350, 275, 50);
        paneAnalytics.add(lblGeneration4);
        
        lblGeneration4Perc = new JLabel("0.00%"+"               "+0);
        lblGeneration4Perc.setBounds(115, 350, 275, 50);
        paneAnalytics.add(lblGeneration4Perc);
        
        btnSignOut = new JButton("Sign-Out");
        btnSignOut.setBounds(675,515,100,30);
        btnSignOut.addActionListener(this);
        add(btnSignOut);
        
}
    //function that creates panel per position
    public JPanel addPage(String Position, HashMap<Integer,Integer> hm){
        
        JPanel page = new JPanel();
        page.add(new JLabel(Position));
        
        String[] columnNames = {"Candidate", "Party","Votes %"};
    
        DefaultTableModel model = new DefaultTableModel(null,columnNames){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        for (int ID : hm.keySet()){
            Candidate candidate = new Candidate(ID);
            if(Position.equals("Senator")){
                String[] arrCandidateRow = {candidate.name,candidate.partylist,perCon.addPercent(hm.get(ID),(float)intTotalVotes*6)};
            model.addRow(arrCandidateRow);
            }else{
            String[] arrCandidateRow = {candidate.name,candidate.partylist,perCon.getPercentString(hm.get(ID),intTotalVotes)};
            model.addRow(arrCandidateRow);
            }
        }
                
        JTable jtCandidate = new JTable(model);
        jtCandidate.setBounds(0,0,490,490);
        jtCandidate.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtCandidate.getColumnModel().getColumn(0).setPreferredWidth(250);
        jtCandidate.getColumnModel().getColumn(2).setPreferredWidth(30);
        JScrollPane spTab1 = new JScrollPane(jtCandidate);
        spTab1.setBounds(10, 5, 500, 500);
        page.add(spTab1);

    //table action
        jtCandidate.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        @Override
            public void valueChanged(ListSelectionEvent event){
                if(!event.getValueIsAdjusting()){
                    int selectedRow = jtCandidate.getSelectedRow();
                                       
                    if(selectedRow != -1){
                        String candidateName = (String)jtCandidate.getValueAt(selectedRow, 0);
                        lblAnalyticsCandidate.setText(candidateName);
                        
                        int candidateID = new Candidate(candidateName).candidateID;
                        int[] candidateAnalyics = analytics.getCandidateStatistics(candidateID);
                        intTotalCandidateVotes = hm.get(candidateID);
                        
                   //placeholder for votes based on sex :3
                        int voteCountMale = candidateAnalyics[0];
                        int voteCountFemale = candidateAnalyics[1];
                        lblAnalyticsVotes.setText(perCon.getPercentString(intTotalCandidateVotes, analytics.votePerPosition(Position))+ " " + "  -  " + String.valueOf(hm.get(candidateID)) + " Votes" );
                        lblFemaleVotesPerc.setText((perCon.getPercentString(voteCountFemale,intTotalCandidateVotes))+"               "+voteCountFemale);
                        lblMaleVotesPerc.setText((perCon.getPercentString(voteCountMale, intTotalCandidateVotes))+"               "+voteCountMale);                  
                        
                   //placeholder for votes based on age
                        int voteCountGen1 = candidateAnalyics[2];
                        int voteCountGen2 = candidateAnalyics[3];
                        int voteCountGen3 = candidateAnalyics[4];
                        int voteCountGen4 = candidateAnalyics[5];
                        lblGeneration1Perc.setText((perCon.getPercentString(voteCountGen1,intTotalCandidateVotes))+"               "+voteCountGen1);
                        lblGeneration2Perc.setText((perCon.getPercentString(voteCountGen2, intTotalCandidateVotes))+"               "+voteCountGen2);
                        lblGeneration3Perc.setText((perCon.getPercentString(voteCountGen3,intTotalCandidateVotes))+"               "+voteCountGen3);
                        lblGeneration4Perc.setText((perCon.getPercentString(voteCountGen4, intTotalCandidateVotes))+"               "+voteCountGen4);  
                    
                        jtCandidate.clearSelection();
                    }
                    
                }
        }
        });
    
    return page;
    }   

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSignOut){
            dispose();
            new frameLogin().setVisible(rootPaneCheckingEnabled);
        }
    }
}
