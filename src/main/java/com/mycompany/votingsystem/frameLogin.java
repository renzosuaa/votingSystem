package com.mycompany.votingsystem;

//Aiello Gabriel B. Lastrella

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class frameLogin extends JFrame implements ActionListener {

    // for GUI
    private JLabel lblLogo, lblCOE, lblOrg,lblEmail, lblPassword;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnRegister;

    private TimeFuncElection time = new TimeFuncElection();
    private Voter voter = new Voter();

    frameLogin() {
        
        frameRegistration.data_HT();

        setTitle("Login Page");
        setSize(800, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Logo
        lblLogo = new JLabel();
        lblLogo.setBounds(98, 50, 230, 380); 
        ImageIcon logoIcon = new ImageIcon("Commission on Elections.png");
        lblLogo.setIcon(new ImageIcon(logoIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        add(lblLogo);
        
        //Label Logoname    
        lblCOE = new JLabel("COMMISION ON ELECTIONS");
        lblCOE.setBounds(95, 50, 400, 600);
        lblCOE.setFont(new Font ("Arial",Font.BOLD,16));
        add(lblCOE);
        
        //label logoname
        lblOrg = new JLabel("IBITS-BC");
        lblOrg.setBounds(160, 50, 400, 650);
        lblOrg.setFont(new Font ("Arial",Font.BOLD,16));
        add(lblOrg);
        
        
        // for Email
        lblEmail = new JLabel("Email: ");
        lblEmail.setBounds(385, 110, 80, 50);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(385, 150, 300, 50);
        add(txtEmail);

        
        // for Password
        lblPassword = new JLabel("Password: ");
        lblPassword.setBounds(385, 200, 80, 50);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(385, 240, 300, 50);
        add(txtPassword);

        
        // for Login
        btnLogin = new JButton("Login");
        btnLogin.setBounds(385, 310, 300, 50);
        add(btnLogin);
        
        // for Registration / Creating an Account
        btnRegister = new JButton("Create Account");
        btnRegister.setBounds(460, 375, 150, 50);
        add(btnRegister);
        
        
        // for the functions of the buttons to work
        btnLogin.addActionListener(this);
        btnRegister.addActionListener(this);
    }

    
    // function of the buttons
    @Override
    public void actionPerformed(ActionEvent e) {   
        if (e.getSource() == btnLogin){

            String email = txtEmail.getText();
            String password = new String(txtPassword.getPassword());

            // FOR ADMINS: retrieve data sa adminAccounts then will be redirected to frameAdminAccess (if details are correct)
            if (voter.isAdmin(email, password)) {
                JOptionPane.showMessageDialog(btnLogin, "Admin Login successful!", "Admin", JOptionPane.INFORMATION_MESSAGE);
                new frameAdminAccess().setVisible(true); 
                dispose();
             
            // FOR USERS: retirve data sa hash table then will be redirected to either frameVoting/Waiting depending on set schedule  
            } else if (frameRegistration.retrieve_HT().containsKey(email) &&
                       frameRegistration.retrieve_HT().get(email).equals(password)) {
                       JOptionPane.showMessageDialog(btnLogin, "Login successful!", "Hash Table", JOptionPane.INFORMATION_MESSAGE);
                    
                        //Checks whether the current time is within the set Election time
                        if(time.isWithinTime()){
                            dispose();
                            new frameVoting().setVisible(true);
                        }
                        else if(time.isAfterTime()){
                            dispose();
                            JOptionPane.showMessageDialog(null, "Insert Analytics here", "womp womp",JOptionPane.ERROR_MESSAGE);
                            new frameLogin().setVisible(true);
                        }
                        else{
                            dispose();
                            new frameWaitingPage().setVisible(true);
                        }
                        dispose();
                    
            } else {
                JOptionPane.showMessageDialog(btnLogin, "Email and Password must be correct.", "Error", JOptionPane.ERROR_MESSAGE);  
            }
               
        }else if (e.getSource() == btnRegister){
            dispose();
            new frameRegistration().setVisible(true);
        }
    }
}