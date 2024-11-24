/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.votingsystem;

/**
 *
 * @author lastr
 */


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;



public class frameLogin extends JFrame {

    // for GUI
    private JLabel lblEmail, lblPassword;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnRegister;

    
    // for Database
    static final String URL = "jdbc:mysql://localhost:3306/login";
    static final String USER = "root"; 
    static final String PASSWORD = "aiellogabriel11924lastrella"; 


    frameLogin() {

        setSize(800, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // for Email
        lblEmail = new JLabel("Email: ");
        lblEmail.setBounds(375, 110, 80, 50);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(375, 150, 300, 50);
        add(txtEmail);

        // for Password
        lblPassword = new JLabel("Password: ");
        lblPassword.setBounds(375, 200, 80, 50);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(375, 240, 300, 50);
        add(txtPassword);

        /* Function of Login Button. Admin will be redirected to frameAdmin if specific email and password were used.
        Then voters need to register first to be able to enter the voting frame*/
        btnLogin = new JButton("Login");
        btnLogin.setBounds(375, 310, 300, 50);
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                String password = new String(txtPassword.getPassword());

                // example of specific email and password for Admin
                if ("admin01@gmail.com".equals(email) && "admin0123".equals(password)) {
                    JOptionPane.showMessageDialog(btnLogin, "Login successful!");
                    new frameAdminAccess(); 
                    dispose(); 
                } else {
                    
                    // function to retrieve data from database so voters could login
                    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                        String query = "SELECT * FROM login.registration WHERE email = ? AND password = ?";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, email);
                        preparedStatement.setString(2, password);

                        ResultSet set = preparedStatement.executeQuery();
                        if (set.next()) {
                            JOptionPane.showMessageDialog(btnLogin, "Login successful!");
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
        });
        add(btnLogin);

        // button that will redirect voters to registration frame so they could create an account
        btnRegister = new JButton("Create Account");
        btnRegister.setBounds(450, 375, 150, 50);
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new frameRegistration();
            }
        });
        add(btnRegister);

        setVisible(true); 
    }
    
    
    // Example of Voting Frame (since we haven't started creating it yet)
    class frameVoting extends JFrame {
        frameVoting() {
            setSize(800, 600);
            setLayout(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel welcomeLabel = new JLabel("Welcome to the Home Page!");
            welcomeLabel.setBounds(50, 50, 300, 50);
            add(welcomeLabel);

            setVisible(true);
        }
    }
   
}

    
