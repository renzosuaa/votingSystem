package com.mycompany.votingsystem;

//Aiello Gabriel B. Lastrella

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;


public class frameLogin extends JFrame implements ActionListener {

    // for GUI
    private JLabel lblLogo, lblCOE, lblOrg,lblEmail, lblPassword;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnRegister;

    // for Database
    static final String URL = "jdbc:mysql://localhost:3306/login";
    static final String USER = "root"; 
    static final String PASSWORD = "aiellogabriel11924lastrella"; 

    TimeFuncElection time = new TimeFuncElection();

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

            // a set admin account and password to access admin frame
            if ("admin01@gmail.com".equals(email) && "admin0123".equals(password)) {
                JOptionPane.showMessageDialog(btnLogin, "Login successful!");
                new frameAdminAccess().setVisible(true); 
                dispose(); 
            } else {
                
                //Checks whether the current time is within the set Election time
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbvotingsystem","root","Andurehhh619");
                    PreparedStatement ps = con.prepareStatement("Select startDateTime,endDateTime from dbvotingsystem.electiondate");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()){
                        String strStartDateTime = rs.getString(1);
                        System.out.println(strStartDateTime);
                        LocalDateTime ldtStartDateTime = LocalDateTime.parse(strStartDateTime,DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
                        String strEndDateTime = rs.getString(2);
                        LocalDateTime ldtEndDateTime = LocalDateTime.parse(strEndDateTime,DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
                        con.close();
                            if(time.isWithinTime(ldtStartDateTime, ldtEndDateTime)){
                                dispose();
                                new frameVoting().setVisible(true);
                            }
                            else{
                                dispose();
                                new frameWaitingPage().setVisible(true);
                            }
                    }   

                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);   
                }
                
                
                // Check if the email and password are in the hash table
                if (frameRegistration.retrieve_HT().containsKey(email) &&
                    frameRegistration.retrieve_HT().get(email).equals(password)) {
                    JOptionPane.showMessageDialog(btnLogin, "Login successful!", "Hash Table", JOptionPane.INFORMATION_MESSAGE);
                    new frameVoting().setVisible(true);
                    dispose();
                } else {
                    // Check if an account is already in database
                    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                        String query = "SELECT * FROM login.registration WHERE email = ? AND password = ?";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, email);
                        preparedStatement.setString(2, password);

                        ResultSet set = preparedStatement.executeQuery();
                        if (set.next()) {
                            JOptionPane.showMessageDialog(btnLogin, "Login successful!", "Database", JOptionPane.INFORMATION_MESSAGE);
                            new frameVoting();

                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(btnLogin, "Invalid username or password.",
                                    "Login Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(btnLogin, "An error occurred during authentication.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

        else if (e.getSource() == btnRegister){
            dispose();
            new frameRegistration().setVisible(true);
        } 
    }
}

    
