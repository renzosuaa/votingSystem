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

    private JLabel lblEmail, lblPassword;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnCreateAccount;

    
    static final String URL = "jdbc:mysql://localhost:3306/login";
    static final String USER = "root"; 
    static final String PASSWORD = "aiellogabriel11924lastrella"; 


    frameLogin() {

        setSize(800, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
 
        
        lblEmail = new JLabel("Email: ");
        lblEmail.setBounds(375, 110, 80, 50);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(375, 150, 300, 50);
        add(txtEmail);

        
        lblPassword = new JLabel("Password: ");
        lblPassword.setBounds(375, 200, 80, 50);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(375, 240, 300, 50);
        add(txtPassword);

        
        btnLogin = new JButton("Login");
        btnLogin.setBounds(375, 310, 300, 50);
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                String password = new String(txtPassword.getPassword());

                
                if ("admin01@gmail.com".equals(email) && "admin0123".equals(password)) {
                    JOptionPane.showMessageDialog(btnLogin, "Login successful!");
                    new frameAdminAccess(); 
                    dispose(); 
                } else {
    
                    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                        String query = "SELECT * FROM login.registration WHERE email = ? AND password = ?";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, email);
                        preparedStatement.setString(2, password);

                        ResultSet set = preparedStatement.executeQuery();
                        if (set.next()) {
                            JOptionPane.showMessageDialog(btnLogin, "Login successful!");
                            new userPage(); 
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

        
        btnCreateAccount = new JButton("Create Account");
        btnCreateAccount.setBounds(450, 375, 150, 50);
        btnCreateAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new createAccountFrame();
            }
        });
        add(btnCreateAccount);

        setVisible(true); 
    }
    
    

    // Example ng Admin Access para may ma call kapag Login Successful
    class adminAccess extends JFrame {
        adminAccess() {
            setSize(800, 600);
            setLayout(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel welcomeLabel = new JLabel("Welcome to the Home Page!");
            welcomeLabel.setBounds(50, 50, 300, 50);
            add(welcomeLabel);

            setVisible(true);
        }
    }
    
    
    // Example ng Users' Page para may ma call kapag Login Successful
    class userPage extends JFrame {
        userPage() {
            setSize(800, 600);
            setLayout(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel welcomeLabel = new JLabel("Welcome to the Home Page!");
            welcomeLabel.setBounds(50, 50, 300, 50);
            add(welcomeLabel);

            setVisible(true);
        }
    }
    

    
    // Registration Form 
    class createAccountFrame extends JFrame {
        private JLabel lblCreate, lblEmail, lblFirstName, lblMiddleName, lblLastName, lblBirthday, 
                       lblMonth, lblDay, lblYear, lblGender, lblPassword, lblConfirmPassword;
        private JTextField txtEmail, txtFirstName, txtMiddleName, txtLastName, txtMonth, txtDay,
                       txtYear;
        private JRadioButton rbtnMale, rbtnFemale;
        private JPasswordField txtPassword, txtConfirmPassword;
        

        createAccountFrame() {
            setSize(800, 600);
            setLayout(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            lblCreate = new JLabel("Create Account");
            lblCreate.setBounds(350, 15, 150, 70);
            add(lblCreate);
            
            //Email
            lblEmail = new JLabel("Email:");
            lblEmail.setBounds(180, 75, 150, 30);
            add(lblEmail);

            txtEmail = new JTextField();
            txtEmail.setBounds(300, 75, 250, 30);
            add(txtEmail);
            
            //First Name
            lblFirstName = new JLabel("First Name:");
            lblFirstName.setBounds(180, 120, 150, 30);
            add(lblFirstName);
            
            txtFirstName = new JTextField();
            txtFirstName.setBounds(300, 120, 250, 30);
            add(txtFirstName);
            
            //Middle Name
            lblMiddleName = new JLabel("Middle Name:");
            lblMiddleName.setBounds(180, 165, 150, 30);
            add(lblMiddleName);
            
            txtMiddleName = new JTextField();
            txtMiddleName.setBounds(300, 165, 250, 30);
            add(txtMiddleName);
            
            //Last Name
            lblLastName = new JLabel("Last Name:");
            lblLastName.setBounds(180, 210, 150, 30);
            add(lblLastName);
            
            txtLastName = new JTextField();
            txtLastName.setBounds(300, 210, 250, 30);
            add(txtLastName);
            
            //Birthday
            lblBirthday = new JLabel("Birthday:");
            lblBirthday.setBounds(180, 275, 150, 30);
            add(lblBirthday);
            
            lblMonth = new JLabel("MM");
            lblMonth.setBounds(315, 255, 30, 25);
            add(lblMonth);
            
            txtMonth = new JTextField();
            txtMonth.setBounds(300, 275, 50, 30);
            add(txtMonth);
            
            lblDay = new JLabel("DD");
            lblDay.setBounds(375, 255, 30, 25);
            add(lblDay);

            txtDay = new JTextField();
            txtDay.setBounds(360, 275, 50, 30);
            add(txtDay);
            
            lblYear = new JLabel("YYYY");
            lblYear.setBounds(443, 255, 80, 25);
            add(lblYear);

            txtYear = new JTextField();
            txtYear.setBounds(420, 275, 80, 30);
            add(txtYear);
            
            //Gender
            lblGender = new JLabel("Gender:");
            lblGender.setBounds(180, 320, 150, 30);
            add(lblGender);
            
            rbtnMale = new JRadioButton("Male");
            rbtnMale.setBounds(300, 320, 80, 30);
            add(rbtnMale);
            
            rbtnFemale = new JRadioButton("Female");
            rbtnFemale.setBounds(375, 320, 80, 30);
            add(rbtnFemale);
            
            //Password
            lblPassword = new JLabel("Password:");
            lblPassword.setBounds(180, 370, 150, 30);
            add(lblPassword);

            txtPassword = new JPasswordField();
            txtPassword.setBounds(300, 370, 250, 30);
            add(txtPassword);
            
            //Confirm Password
            lblConfirmPassword = new JLabel("Confirm Password:");
            lblConfirmPassword.setBounds(180, 415, 150, 30);
            add(lblConfirmPassword);
            
            txtConfirmPassword = new JPasswordField();
            txtConfirmPassword.setBounds(300, 415, 250, 30);
            add(txtConfirmPassword);
            
            
            JButton createButton = new JButton("Create");
            createButton.setBounds(350, 470, 100, 30);
            createButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String email = txtEmail.getText();
                    String firstName = txtFirstName.getText();
                    String middleName = txtMiddleName.getText();
                    String lastName = txtLastName.getText();
                    
                    String mm = txtMonth.getText();
                    String dd = txtDay.getText();
                    String yyyy = txtYear.getText();
                    String birthdayFormat = String.format("%s-%s-%s", mm, dd, yyyy);
                    
                    String gender = null;
                    if (rbtnMale.isSelected()) {
                        gender = "Male";
                    } else if (rbtnFemale.isSelected()) {
                        gender = "Female";
                    }
                    
                    String password = new String(txtPassword.getPassword());
                    String conPassword = new String(txtConfirmPassword.getPassword());                 
                    

                    // Para sure kung may laman yung parts ng form
                    if (email.isEmpty() || firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty() || mm.isEmpty() ||
                        dd.isEmpty() || yyyy.isEmpty() || password.isEmpty() || conPassword.isEmpty()) {
                        JOptionPane.showMessageDialog(createButton, "Text fields cannot be empty.");
                        return;
                    }
                    
                    
                    // Para sure kung hindi sobra or kulang yung numbers sa birthday
                    if (!mm.matches("\\d{1,2}") || !dd.matches("\\d{1,2}") || !yyyy.matches("\\d{4}")) {
                        JOptionPane.showMessageDialog(createButton, "Birthday must be in correct format.");
                        return;
                    }
                    
                    
                    // Para sure kung numeric format yung nilagay sa birthday (based on Luna's work)
                    LocalDate birthday;
                    try {
                        birthday = LocalDate.of(Integer.parseInt(yyyy), Integer.parseInt(mm), Integer.parseInt(dd));
                    } catch (DateTimeException ex) {
                        JOptionPane.showMessageDialog(createButton, "Birthday must be in numeric form..");
                        return;
                    }

                    
                    // Para sure kung legal age na (based on Luna's work)
                    LocalDate present = LocalDate.now();
                    int age = present.getYear() - birthday.getYear();
                    
                    if (age < 18) {
                        JOptionPane.showMessageDialog(createButton, "You are not yet legal toregister.");
                        return;
                    }
                 
                   
                    // Confirm Password (based 'to sa gawa nila Luna)
                    if (!password.equals(conPassword)) {
                        JOptionPane.showMessageDialog(createButton, "Passwords do not match.");
                        return;
                    }

                   
                    // Para sure kung wala pang katulad yung email ssa Database
                    try (Connection connection = DriverManager.getConnection(frameLogin.URL, frameLogin.USER, frameLogin.PASSWORD)) {
                       String query = "SELECT COUNT(*) FROM login.registration WHERE email = ?";
                       PreparedStatement preparedStatement = connection.prepareStatement(query);
                       preparedStatement.setString(1, email);
                       ResultSet rS = preparedStatement.executeQuery();

                       if (rS.next() && rS.getInt(1) > 0) {
                           JOptionPane.showMessageDialog(createButton, "Email already exists in the database. Please choose another.");
                           return;
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        JOptionPane.showMessageDialog(createButton, "Database error: " + exception.getMessage());
                        return;
                    }
                    
                    
                    // Para ma-insert yung data sa Database
                    try {
                        int id = createID(); 
                        Connection connection = DriverManager.getConnection(frameLogin.URL, frameLogin.USER, frameLogin.PASSWORD);
                        
                        String query = "INSERT INTO login.registration (id, email, firstname, middlename, lastname,"
                                + "birthday, gender, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        
                        preparedStatement.setInt(1, id);
                        preparedStatement.setString(2, email);
                        preparedStatement.setString(3, firstName);
                        preparedStatement.setString(4, middleName);
                        preparedStatement.setString(5, lastName);
                        preparedStatement.setString(6, birthdayFormat);
                        preparedStatement.setString(7, gender);
                        preparedStatement.setString(8, password); 

                        int result = preparedStatement.executeUpdate();
                        txtEmail.setText("");
                        txtFirstName.setText("");
                        txtMiddleName.setText("");
                        txtLastName.setText("");
                        txtMonth.setText("");
                        txtDay.setText("");
                        txtYear.setText("");
                        rbtnMale.setText("");
                        rbtnFemale.setText("");
                        txtPassword.setText("");
                        txtConfirmPassword.setText("");
                        if (result > 0) {
                            JOptionPane.showMessageDialog(createButton, "Account created successfully!");
  
                        } else {
                            JOptionPane.showMessageDialog(createButton, "Error creating account.");
                        }
                        connection.close();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        JOptionPane.showMessageDialog(createButton, "Database error: " + exception.getMessage());
                    }
                    dispose();
                }
            });
            add(createButton);

            setVisible(true);
        }
        
        
        // Para may specif na ID per users (not sure yet in this part, but working totally fine)
        private int createID() {
            String query = "SELECT COALESCE(MAX(id), 0) + 1 FROM login.registration";
            try (Connection connection = DriverManager.getConnection(frameLogin.URL, frameLogin.USER, frameLogin.PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet info = preparedStatement.executeQuery()) {

                if (info.next()) {
                    return info.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 1;
        }
    }
   
}

    
