/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.votingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Enumeration;

public class frameVoting extends JFrame implements ActionListener {

    private JLabel lblCandidateName, lblCandidateId, lblPres, lblVpres, lblSenators;
    private JScrollPane MainScrollPane, InnerScrollPane1, InnerScrollPane2, InnerScrollPane3;
    private JPanel Mainpanel, presidentPanel, vicePresidentPanel, senatorPanel;
    private JButton btnSubmit, btnSignOut;

    // Radio buttons for President and Vice President
    private ButtonGroup presidentGroup, vicePresidentGroup;
    
    private ArrayList<JCheckBox> senatorCheckBoxes = new ArrayList<>(); // For senators

    public frameVoting() {
        setSize(800, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevent closing unless explicitly done
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

        lblCandidateName = new JLabel("Candidates Name");
        lblCandidateName.setFont(new Font("Arial", Font.BOLD, 25));
        lblCandidateName.setBounds(30, 30, 700, 30);
        add(lblCandidateName);

        lblCandidateId = new JLabel("Candidate_ID");
        lblCandidateId.setFont(new Font("Arial", Font.BOLD, 15));
        lblCandidateId.setBounds(40, 60, 700, 30);
        add(lblCandidateId);

        btnSubmit = new JButton("SUBMIT");
        btnSubmit.setBounds(450, 515, 120, 30);
        add(btnSubmit);

        btnSignOut = new JButton("SIGN OUT");
        btnSignOut.setBounds(600, 515, 110, 30);
        add(btnSignOut);

        // Button listeners
        btnSubmit.addActionListener(this);
        btnSignOut.addActionListener(this);

        // Button groups for Radio buttons
        presidentGroup = new ButtonGroup();
        vicePresidentGroup = new ButtonGroup();

        setVisible(true);
    }

    // Method to add a candidate to the platform
    public void addCandidate(String firstName, String lastName, String position) {
        String candidateName = firstName + " " + lastName;

        // Create a panel to hold the name and button (Radio or Checkboxes)
        JPanel candidatePanel = new JPanel();
        candidatePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        if (position.equalsIgnoreCase("President")) {
            // Create RadioButton for President
            JRadioButton radioButton = new JRadioButton(candidateName);
            presidentGroup.add(radioButton);
            candidatePanel.add(radioButton);
            presidentPanel.add(candidatePanel);
        } else if (position.equalsIgnoreCase("Vice President")) {
            // Create RadioButton for Vice President
            JRadioButton radioButton = new JRadioButton(candidateName);
            vicePresidentGroup.add(radioButton);
            candidatePanel.add(radioButton);
            vicePresidentPanel.add(candidatePanel);
        } else if (position.equalsIgnoreCase("Senator")) {
            // Create Checkbox for Senators
            JCheckBox checkBox = new JCheckBox(candidateName);
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

    // Handle the limitation of 6 Senator votes
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

    @Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnSubmit) {
        // Collect the selected President
        String selectedPresident = getSelectedCandidate(presidentGroup);
        // Collect the selected Vice President
        String selectedVicePresident = getSelectedCandidate(vicePresidentGroup);
        // Collect the selected Senators
        ArrayList<String> selectedSenators = getSelectedSenators();

        // Display the vote summary in a preview dialog
        StringBuilder voteSummary = new StringBuilder();
        voteSummary.append("Your Selected Votes:\n\n");

        // Display President and Vice President
        voteSummary.append("President: ").append(selectedPresident).append("\n");
        voteSummary.append("Vice President: ").append(selectedVicePresident).append("\n");

        // Display Senators
        voteSummary.append("Senators: ");
        if (selectedSenators.isEmpty()) {
            voteSummary.append("None selected\n");
        } else {
            voteSummary.append(String.join(", ", selectedSenators)).append("\n");
        }

        // Show the summary
        JOptionPane.showMessageDialog(this, voteSummary.toString(), "Your Voting Summary", JOptionPane.INFORMATION_MESSAGE);

        // Close the voting platform after submission
        dispose();
    } else if (e.getSource() == btnSignOut) {
        JOptionPane.showMessageDialog(this, "Thank You for Voting Wisely!", "Voting Guide", JOptionPane.INFORMATION_MESSAGE);
        dispose();  // Close the frame when sign out is clicked
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