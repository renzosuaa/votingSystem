package com.mycompany.votingsystem;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public final class frameVoting extends JFrame implements ActionListener {

    private final JLabel lblVoterName, lblVoterId, lblPres, lblVpres, lblSenators;
    private final JScrollPane MainScrollPane, InnerScrollPane1, InnerScrollPane2, InnerScrollPane3;
    private final JPanel Mainpanel, presidentPanel, vicePresidentPanel, senatorPanel;
    private final JButton btnSubmit, btnSignOut;
    private final Vote vote = new Vote();
    private final Voter voter;
    private final Candidate candidate = new Candidate();

    // Radio buttons for President and Vice President
    private final ButtonGroup presidentGroup, vicePresidentGroup;
    private final ArrayList<JCheckBox> senatorCheckBoxes = new ArrayList<>(); // For senators

    public frameVoting(Voter voter) {
        setSize(800, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Prevent closing unless explicitly done
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Voting Selection");

        Mainpanel = new JPanel();
        Mainpanel.setLayout(null);
        Mainpanel.setBackground(Color.LIGHT_GRAY);

        // Labels for each position
        lblPres = new JLabel("President");
        lblPres.setFont(new Font("Arial", Font.BOLD, 15));
        lblPres.setBounds(30, 20, 200, 30);
        Mainpanel.add(lblPres);

        lblVpres = new JLabel("Vice President");
        lblVpres.setFont(new Font("Arial", Font.BOLD, 15));
        lblVpres.setBounds(30, 135, 200, 30);
        Mainpanel.add(lblVpres);

        lblSenators = new JLabel("Senators");
        lblSenators.setFont(new Font("Arial", Font.BOLD, 15));
        lblSenators.setBounds(30, 260, 200, 30);
        Mainpanel.add(lblSenators);

        // Panels for each position
        presidentPanel = new JPanel();
        presidentPanel.setLayout(new GridLayout(0, 3)); // 3 candidates per row
        InnerScrollPane1 = new JScrollPane(presidentPanel);
        InnerScrollPane1.setBounds(100, 160, 570, 75);
        add(InnerScrollPane1);

        vicePresidentPanel = new JPanel();
        vicePresidentPanel.setLayout(new GridLayout(0, 3)); // 3 candidates per row
        InnerScrollPane2 = new JScrollPane(vicePresidentPanel);
        InnerScrollPane2.setBounds(100, 265, 570, 95);
        add(InnerScrollPane2);

        senatorPanel = new JPanel();
        senatorPanel.setLayout(new GridLayout(0, 4)); // 4 candidates per row
        InnerScrollPane3 = new JScrollPane(senatorPanel);
        InnerScrollPane3.setBounds(100, 395, 570, 90);
        add(InnerScrollPane3);

        MainScrollPane = new JScrollPane(Mainpanel);
        MainScrollPane.setBounds(50, 100, 680, 400);
        add(MainScrollPane);

        lblVoterName = new JLabel(voter.name);
        lblVoterName.setFont(new Font("Arial", Font.BOLD, 25));
        lblVoterName.setBounds(30, 30, 700, 30);
        add(lblVoterName);

        lblVoterId = new JLabel("Voter ID: " + voter.voterID);
        lblVoterId.setFont(new Font("Arial", Font.BOLD, 15));
        lblVoterId.setBounds(40, 60, 700, 30);
        add(lblVoterId);

        btnSubmit = new JButton("SUBMIT");
        btnSubmit.setBounds(450, 515, 120, 30);
        add(btnSubmit);

        btnSignOut = new JButton("SIGN OUT");
        btnSignOut.setBounds(600, 515, 110, 30);
        add(btnSignOut);

        btnSubmit.addActionListener(this);
        btnSignOut.addActionListener(this);

        presidentGroup = new ButtonGroup();
        vicePresidentGroup = new ButtonGroup();
        
        addAllCandidates("President");
        addAllCandidates("Vice President");
        addAllCandidates("Senator");
        
        this.voter = voter;
        
        setVisible(true);
    }
    
    //Function to add all candidates to their respective ButtonGroup
    private void addAllCandidates(String position){
        LinkedList <Integer> candidates = new LinkedList(candidate.listOfAllCandidates(position));
        
        for (int candidateIDs: candidates){
            Candidate _candidate = new Candidate(candidateIDs);
            addCandidates(_candidate.name, position, _candidate.partylist);
        }
    }

    // Method to add a candidate to the platform
    public void addCandidates(String name, String position, String partylist) {
        String candidateName = name;

        // Create a panel to hold the name and button (Radio or Checkboxes)
        JPanel candidatePanel = new JPanel();
        candidatePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        if (position.equalsIgnoreCase("President")) {
            JRadioButton radioButton = new JRadioButton(candidateName);
            radioButton.setToolTipText("PARTYLIST: "+partylist);
            presidentGroup.add(radioButton);
            candidatePanel.add(radioButton);
            presidentPanel.add(candidatePanel);
        } else if (position.equalsIgnoreCase("Vice President")) {
            JRadioButton radioButton = new JRadioButton(candidateName);
            radioButton.setToolTipText("PARTYLIST: "+partylist);
            vicePresidentGroup.add(radioButton);
            candidatePanel.add(radioButton);
            vicePresidentPanel.add(candidatePanel);
        } else if (position.equalsIgnoreCase("Senator")) {
            JCheckBox checkBox = new JCheckBox(candidateName);
           checkBox.setToolTipText("PARTYLIST: "+partylist);
            checkBox.addItemListener(e -> handleSenatorSelection(checkBox));
            senatorCheckBoxes.add(checkBox);
            candidatePanel.add(checkBox);
            senatorPanel.add(candidatePanel);
        }

        // Revalidate and repaint the panels to display the updated candidates
        presidentPanel.revalidate();
        vicePresidentPanel.revalidate();
        senatorPanel.revalidate();
    }

    // Limits the selected senators to 6
    private void handleSenatorSelection(JCheckBox checkBox) {
        int selectedCount = 0;
        for (JCheckBox box : senatorCheckBoxes) {
            if (box.isSelected()) {
                selectedCount++;
            }
        }

        if (selectedCount > 6) {
            checkBox.setSelected(false); // Deselect the current checkbox if 6 selections are already made
            JOptionPane.showMessageDialog(this, "You can only select up to 6 Senators.", "Voting Limit Exceeded", JOptionPane.WARNING_MESSAGE);
        }
    }
    
//Regala and Luna
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmit) {

            String selectedPresident = getSelectedCandidate(presidentGroup);
            String selectedVicePresident = getSelectedCandidate(vicePresidentGroup);
            ArrayList<String> selectedSenators = getSelectedSenators();


            StringBuilder errorMessage = new StringBuilder();
            if ("None selected".equals(selectedPresident)) {
                errorMessage.append("No President selected. Please vote for a President.\n\n");
            }
            if ("None selected".equals(selectedVicePresident)) {
                errorMessage.append("No Vice President selected. Please vote for a Vice President.\n");
            }

            if (errorMessage.length() > 0) {
                JOptionPane.showMessageDialog(
                    this, 
                    errorMessage.toString(), 
                    "Incomplete Votes", 
                    JOptionPane.ERROR_MESSAGE
                );
                return; 
            }

            // Add "Abstain" entries if fewer than 6 senators are selected
            while (selectedSenators.size() < 6) {
                selectedSenators.add("Abstain Senator");
            }

            // Build and display the vote summary
            StringBuilder voteSummary = new StringBuilder();
            voteSummary.append("Your Selected Votes:\n\n");
            voteSummary.append("President: ").append(selectedPresident).append("\n");
            voteSummary.append("Vice President: ").append(selectedVicePresident).append("\n");
            voteSummary.append("Senators: ").append(String.join(", ", selectedSenators)).append("\n\n");
            voteSummary.append("Are You Sure of Your Vote?");
            int answer = JOptionPane.showConfirmDialog(this, voteSummary.toString(),"Vote Summary",JOptionPane.YES_NO_OPTION);

            if (answer == 0){
                idGenerator id = new idGenerator();
                vote.addVote(id.idGenerator("dbVotingSystem", "Vote", "voteID"), voter.voterID, new Candidate(selectedPresident).candidateID);
                vote.addVote(id.idGenerator("dbVotingSystem", "Vote", "voteID"), voter.voterID, new Candidate(selectedVicePresident).candidateID);
                for (String senators : selectedSenators){
                    vote.addVote(id.idGenerator("dbVotingSystem", "Vote", "voteID"), voter.voterID, new Candidate(senators).candidateID);       
                }
                
                dispose();
                voter.setVoterToVoted();
                JOptionPane.showMessageDialog(senatorPanel, "Voted Successfully!");
                new frameLogin().setVisible(true);
            }

        } else if (e.getSource() == btnSignOut) {
            JOptionPane.showMessageDialog(this, "Thank You for Voting Wisely!",  "Voting Guide", JOptionPane.INFORMATION_MESSAGE);
            dispose();  
            new frameLogin().setVisible(true);
        }
    }


    // Helper method to get the selected candidate from a ButtonGroup (President or Vice President)
    private String getSelectedCandidate(ButtonGroup group) {
        for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return "None selected";  // Return a default value if nothing is selected
    }

    // Helper method to get the selected Senators (up to 6)
    private ArrayList<String> getSelectedSenators() {
        ArrayList<String> selectedSenators = new ArrayList<>();
        for (JCheckBox checkBox : senatorCheckBoxes) {
            if (checkBox.isSelected()) {
                selectedSenators.add(checkBox.getText());
            }
        }
        return selectedSenators;
    }

}