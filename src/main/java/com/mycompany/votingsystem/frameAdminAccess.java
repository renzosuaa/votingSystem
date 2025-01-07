package com.mycompany.votingsystem;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.time.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class frameAdminAccess extends JFrame implements ActionListener {
    
        private JTextArea txtaSummary;
        private JLabel hdrAdmin,hdrAdd,hdrRemove,lblAddName,lblAddParty,lblAddPosition,
                lblRemoveID,hdrElectionDate,lblStartElection,lblEndElection,lblTimeForm,lblDateForm;
        private JComboBox cmbAddPosition;
        private String []position = {"Select Position:","President","Vice President","Senator"} ;
        private JTextField txtfAddName,txtfAddParty,txtfRemoveID,txtfRemoveName,txtfRemovePosition,txtfRemoveParty,
                txtfDateStartElection,txtfDateEndElection, txtfTimeStartElection,txtfTimeEndElection;
        private JButton btnAddCandidate, btnClearCandidate,btnRemoveCandidate,btnSearchCandidate,
                btnSetElection,btnForceEndElection,btnCurrentTime,btnSignOut;
        private JScrollPane spSummary;
        private boolean ElectionOn = false;
        private Candidate candidate = new Candidate();
        private TimeFuncElection timec = new TimeFuncElection();
    
    frameAdminAccess(){
        
        setSize(800,600);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
//Adding Components
        hdrAdmin = new JLabel("Admin Page");
        hdrAdmin.setBounds(10,0,150,30);
        hdrAdmin.setFont(new Font("Helvetica", Font.BOLD, 25));
        add(hdrAdmin);
        
//Add Candidate Function
        hdrAdd = new JLabel("Add Candidate");
        hdrAdd.setBounds(20,30,150,50);
        hdrAdd.setFont(new Font("Arial", Font.ITALIC, 18));
        add(hdrAdd);
        
        lblAddName = new JLabel("Name: ");
        lblAddName.setBounds(20,75,50,50);
        add(lblAddName);
        
        txtfAddName = new JTextField();
        txtfAddName.setBounds(75,80,200,30);
        add(txtfAddName);
                
        lblAddParty = new JLabel("Partylist: ");
        lblAddParty.setBounds(20,125,150,50);
        add(lblAddParty);
        
        txtfAddParty = new JTextField();
        txtfAddParty.setBounds(75,130,200,30);
        add(txtfAddParty);
        
        lblAddPosition = new JLabel("Enter Position: ");
        lblAddPosition.setBounds(20,175,150,50);
        add(lblAddPosition);
        
        cmbAddPosition = new JComboBox(position);
        cmbAddPosition.setBounds(125,180,150,30);
        add(cmbAddPosition);
        
        btnAddCandidate = new JButton("Add Candidate");
        btnAddCandidate.setBounds(325,225,150,30);
        //btnAddCandidate.setBounds(100,225,150,30);
        add(btnAddCandidate);
        
        btnClearCandidate = new JButton("Clear");
        btnClearCandidate.setBounds(230, 225, 75, 30);
        //btnClearCandidate.setBounds(20, 225, 75, 30);
        add(btnClearCandidate);

//Remove Candidate Function
        hdrRemove = new JLabel("Remove Candidate");
        hdrRemove.setBounds(20,250,200,50);
        hdrRemove.setFont(new Font("Arial", Font.ITALIC, 18));
        add(hdrRemove);
        
        lblRemoveID = new JLabel("Enter Candidate ID: ");
        lblRemoveID.setBounds(20, 290,150,50);
        add(lblRemoveID);
        
        txtfRemoveID = new JTextField();
        txtfRemoveID.setBounds(135,305,50,20);
        add(txtfRemoveID);
        
        btnSearchCandidate = new JButton("Search");
        btnSearchCandidate.setBounds(195, 305,80,20);
        add(btnSearchCandidate);
        
        txtfRemoveName = new JTextField();
        txtfRemoveName.setEditable(false);
        txtfRemoveName.setBounds(20,335,225,20);
        add(txtfRemoveName);
        
        txtfRemoveParty = new JTextField();
        txtfRemoveParty.setEditable(false);
        txtfRemoveParty.setBounds(250,335,125,20);
        add(txtfRemoveParty);
        
        txtfRemovePosition= new JTextField();
        txtfRemovePosition.setBounds(380,335,100,20);
        txtfRemovePosition.setEditable(false);
        add(txtfRemovePosition);
        
        btnRemoveCandidate = new JButton("Remove Candidate");
        btnRemoveCandidate.setBounds(325,370,150,30);
        add(btnRemoveCandidate);
       
        txtaSummary = new JTextArea();
        txtaSummary.setEditable(false);
        
        spSummary = new JScrollPane(txtaSummary);
        spSummary.setLayout(new ScrollPaneLayout());
        spSummary.setBounds(490,10,280,390);
        spSummary.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(spSummary);
               
        
        //Election Date:Start Election
        
        hdrElectionDate = new JLabel("Election Date Configuration");
        hdrElectionDate.setBounds(20,400,250,30);
        hdrElectionDate.setFont(new Font("Helvetica", Font.ITALIC,18));
        add(hdrElectionDate);
        
        lblStartElection = new JLabel("Start of Election: ");
        lblStartElection.setBounds(20,465,150,30);
        add(lblStartElection);
        
        lblDateForm = new JLabel("MM/DD/YY");
        lblDateForm.setBounds(135, 440,80, 30);
        add(lblDateForm);
        
        lblTimeForm = new JLabel("HH:MM");
        lblTimeForm.setBounds(240, 440,80, 30);
        add(lblTimeForm);
        
        txtfDateStartElection = new JTextField();
        txtfDateStartElection.setBounds(125,465,80,30);
        add(txtfDateStartElection);
        
       txtfTimeStartElection = new JTextField();
        txtfTimeStartElection.setBounds(225,465,80,30);
        add(txtfTimeStartElection);



        //End Election        
         lblEndElection = new JLabel("End of Election: ");
        lblEndElection.setBounds(20,515,150,30);
        add(lblEndElection);
        
        txtfDateEndElection = new JTextField();
        txtfDateEndElection.setBounds(125,515,80,30);
        add(txtfDateEndElection);

        txtfTimeEndElection = new JTextField();
        txtfTimeEndElection.setBounds(225,515,80,30);
        add(txtfTimeEndElection);

        btnCurrentTime = new JButton("Get Current Time");
        btnCurrentTime.setBounds(325, 465, 150, 30);
        add(btnCurrentTime);
                
        btnSetElection = new JButton("Set Election Date");
        btnSetElection.setBounds(325,515,150,30);
        add(btnSetElection);
        
        btnForceEndElection = new JButton("Force Stop");
        btnForceEndElection.setBounds(550,465,150,30);
        btnForceEndElection.setBackground(Color.RED);
        add(btnForceEndElection);
        
        btnSignOut = new JButton("Sign Out");
        btnSignOut.setBounds(550,515,150,30);
        add(btnSignOut);
        
        btnClearCandidate.addActionListener(this);
        btnCurrentTime.addActionListener(this);
        btnAddCandidate.addActionListener(this);
        btnSignOut.addActionListener(this);
        btnRemoveCandidate.addActionListener(this);
        btnSetElection.addActionListener(this);
        btnSearchCandidate.addActionListener(this);
        btnForceEndElection.addActionListener(this);
        
        updateSummaryTextField();
        
//        setTimeElection setTime = new setTimeElection();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAddCandidate){
                if (((txtfAddName.getText() == null || "".equals(txtfAddParty.getText().trim())) || "".equals(txtfAddName.getText().trim())) || txtfAddParty.getText()==null ){
                    JOptionPane.showMessageDialog(null, "Candidate Information is required.", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (cmbAddPosition.getSelectedIndex() != 0){
                        String name = txtfAddName.getText();
                        String party = txtfAddParty.getText();
                        String candidatePosition = (String)cmbAddPosition.getSelectedItem();
                        candidate.addCandidate(name,party,candidatePosition);
                        txtfAddName.setText("");
                        txtfAddParty.setText("");
                        cmbAddPosition.setSelectedIndex(0);
                        updateSummaryTextField();
                    } else {
                        JOptionPane.showMessageDialog(null, "Select necessary position.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
        }
        else if (e.getSource() ==btnClearCandidate){
            txtfAddName.setText("");
            txtfAddParty.setText("");
            cmbAddPosition.setSelectedIndex(0);
        }
        
    // Output the name, partylist, and position based on the given ID  
        else if (e.getSource() == btnSearchCandidate){
                String i = txtfRemoveID.getText();
                if (!i.trim().equals("")){      
                    try{
                        int ID = Integer.parseInt(i);
                        //search for the name,partylist,and position of the given ID on the database
                        Candidate candidates = new Candidate(ID);
                        txtfRemoveName.setText(candidates.name);
                        txtfRemoveParty.setText(candidates.partylist);
                        txtfRemovePosition.setText(candidates.position);

                    }catch(NumberFormatException ex){
                        //Error Message if the Format of the ID is Invalid
                        JOptionPane.showMessageDialog(null, "Invalid ID");
                    }                  
                }
                else {
                    txtfRemoveID.setText("");
                    JOptionPane.showMessageDialog(null, "Input ID First");  
                }
        }
        
        else if (e.getSource() == btnRemoveCandidate){
                if (!txtfRemoveID.getText().equals("")){  
                    try{
                        int ID = Integer.parseInt(txtfRemoveID.getText());
                // ** will later solve how to implement an error message if the ID does not exist in the data base **          
                        txtfRemoveName.setText("");
                        txtfRemoveParty.setText("");
                        txtfRemovePosition.setText("");
                        candidate.deleteCandidate(ID);
                        txtfRemoveID.setText("");
                        updateSummaryTextField();    
                }catch(NumberFormatException ex){
                    txtfRemoveID.setText("");
                    //Error Message if the Format of the ID is Invalid
                    JOptionPane.showMessageDialog(null, "Invalid ID");   
                }  
                } else{
                    JOptionPane.showMessageDialog(null, "Enter ID First");
                    txtfRemoveID.setText("");
                }
        }
      
        else if (e.getSource() == btnCurrentTime){
                LocalDate currDate = LocalDate.now();
                String formattedCurrDate = currDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                txtfDateStartElection.setText(formattedCurrDate);

                LocalTime currTime = LocalTime.now();
                String formattedCurrTime = currTime.format(DateTimeFormatter.ofPattern("HH:mm"));
                txtfTimeStartElection.setText(formattedCurrTime);
        }
      
        else if(e.getSource() == btnSignOut){
                int resultSignOut = JOptionPane.showConfirmDialog(this, "Are you sure you want to leave the Admin Page?", "Log Out", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if (resultSignOut == JOptionPane.YES_OPTION){
                    dispose();   
                    new frameLogin().setVisible(true);
                } 
        }
        
        else if(e.getSource()==btnSetElection){
            if(!txtfDateEndElection.getText().isBlank() && !txtfTimeEndElection.getText().isBlank() && !txtfDateStartElection.getText().isBlank() && !txtfDateStartElection.getText().isBlank()){
                String StartDateTime = txtfDateStartElection.getText().trim()+" "+txtfTimeStartElection.getText().trim();   
                String EndDateTime = txtfDateEndElection.getText().trim()+" "+txtfTimeEndElection.getText().trim();
                timec.setTime(StartDateTime, EndDateTime);
            }
            else{
                JOptionPane.showMessageDialog(this, "Date of Election Required.","Error",JOptionPane.ERROR_MESSAGE);
            } 
        }
       
        else if(e.getSource()==btnForceEndElection){
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to end the Elections?", "End Election?", JOptionPane.YES_NO_OPTION);
                if(confirm == JOptionPane.YES_OPTION){
                        JOptionPane.showMessageDialog(this, "The election has been forcefully halted.","End Election",JOptionPane.INFORMATION_MESSAGE);
                        ElectionOn = false;
                }   
        }
    }   
    
    //Function used to show the list of candidates on different position by appending their names on the summary (text area)
    void updateSummaryTextField(){
        
        txtaSummary.setText("President \n \n");
        txtaSummary.append(candidate.listOfAllCandidatesInString("President") + "\n");
        txtaSummary.append("Vice President \n \n");
        txtaSummary.append(candidate.listOfAllCandidatesInString("Vice President"));      
    }
      
}
