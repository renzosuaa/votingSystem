package com.mycompany.votingsystem;

// Aiello Gabriel B. Lastrella

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DateTimeException;
import javax.swing.*;
import java.time.LocalDate;
import java.util.Hashtable;
import javax.swing.SwingConstants;

    
public class frameRegistration extends JFrame {

    // components
    private JLabel lblHeader,lblLogo, lblCOE, lblOrg, lblEmail, lblFirstName, lblMiddleName, lblLastName, lblBirthday, 
                   lblGender, lblPassword, lblConfirmPassword;
    private JTextField txtEmail, txtFirstName, txtMiddleName, txtLastName, txtBirthMonth, txtBirthDay, txtBirthYear;
    private JPasswordField txtPassword, txtConfirmPassword;
    private JRadioButton rbtnMale, rbtnFemale;
    private JButton btnCreateAccount, btnCancel, btnBack;
    
    static final String URL = "jdbc:mysql://localhost:3306/login";
    static final String USER = "root"; 
    static final String PASSWORD = "aiellogabriel11924lastrella";
    
    private static Hashtable<String, String> users_HT = new Hashtable<>();

    frameRegistration() {
        
        setTitle("Registration");
        setSize(800, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        lblHeader = new JLabel("Welcome! Please Register", SwingConstants.LEFT);
        lblHeader.setBounds(190, 20, 300, 50);
        lblHeader.setFont(new Font ("Arial",Font.BOLD,20));
        add(lblHeader);
        
        // Logo
        lblLogo = new JLabel();
        lblLogo.setBounds(525, 50, 200,350); 
        ImageIcon logoIcon = new ImageIcon("Commission on Elections.png");
        lblLogo.setIcon(new ImageIcon(logoIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        add(lblLogo);
        
        //Label Logoname    
        lblCOE = new JLabel("COMMISION ON ELECTIONS");
        lblCOE.setBounds(515, 50, 400, 600);
        lblCOE.setFont(new Font ("Arial",Font.BOLD,16));
        add(lblCOE);
        
        //label logoname
        lblOrg = new JLabel("IBITS-BC");
        lblOrg.setBounds(585, 50, 400, 650);
        lblOrg.setFont(new Font ("Arial",Font.BOLD,16));
        add(lblOrg);
        
        // Email
        lblEmail = new JLabel("Email:");
        lblEmail.setBounds(30, 90, 150, 30);
        add(lblEmail);
        txtEmail = new JTextField();
        txtEmail.setBounds(170, 90, 300, 30);
        add(txtEmail);

        // First Name
        lblFirstName = new JLabel("First Name:");
        lblFirstName.setBounds(30, 130, 150, 30);
        add(lblFirstName);
        txtFirstName = new JTextField();
        txtFirstName.setBounds(170, 130, 300, 30);
        add(txtFirstName);

        // Middle Name
        lblMiddleName = new JLabel("Middle Name:");
        lblMiddleName.setBounds(30, 170, 150, 30);
        add(lblMiddleName);
        txtMiddleName = new JTextField();
        txtMiddleName.setBounds(170, 170, 300, 30);
        add(txtMiddleName);

        // Last Name
        lblLastName = new JLabel("Last Name:");
        lblLastName.setBounds(30, 210, 150, 30);
        add(lblLastName);
        txtLastName = new JTextField();
        txtLastName.setBounds(170, 210, 300, 30);
        add(txtLastName);

        // Birthday
        lblBirthday = new JLabel("Birthday (MM/DD/YYYY):");
        lblBirthday.setBounds(30, 250, 150, 30);
        add(lblBirthday);
        
        txtBirthMonth = new JTextField();
        txtBirthMonth.setBounds(170, 250, 50, 30);
        add(txtBirthMonth);

        txtBirthDay = new JTextField();
        txtBirthDay.setBounds(230, 250, 50, 30);
        add(txtBirthDay);

        txtBirthYear = new JTextField();
        txtBirthYear.setBounds(290, 250, 80, 30);
        add(txtBirthYear);

        // Gender
        lblGender = new JLabel("Gender:");
        lblGender.setBounds(30, 290, 150, 30);
        add(lblGender);
        
        rbtnMale = new JRadioButton("Male");
        rbtnMale.setBounds(170, 290, 100, 30);
        add(rbtnMale);
        
        rbtnFemale = new JRadioButton("Female");
        rbtnFemale.setBounds(280, 290, 100, 30);
        add(rbtnFemale);
        
        ButtonGroup gGroup = new ButtonGroup();
        gGroup.add(rbtnMale);
        gGroup.add(rbtnFemale);

      
        // Password
        lblPassword = new JLabel("Password:");
        lblPassword.setBounds(30, 330, 150, 30);
        add(lblPassword);
        txtPassword = new JPasswordField();
        txtPassword.setBounds(170, 330, 300, 30);
        add(txtPassword);

        // Confirm Password
        lblConfirmPassword = new JLabel("Confirm Password:");
        lblConfirmPassword.setBounds(30, 370, 150, 30);
        add(lblConfirmPassword);
        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setBounds(170, 370, 300, 30);
        add(txtConfirmPassword);

        // Create Account Button
        btnCreateAccount = new JButton("Create Account");
        btnCreateAccount.setBounds(250, 420, 130, 30);
        add(btnCreateAccount);
        
        btnBack = new JButton("Back");
        btnBack.setBounds(670, 515, 95, 30);
        add(btnBack);

        btnCreateAccount.addActionListener(this::createAccount);

        btnBack.addActionListener(e -> {
            new frameLogin().setVisible(true);
            dispose();     
        });
    }
    
    // function
    private void createAccount(ActionEvent e) {
        String email = txtEmail.getText();
        String firstName = txtFirstName.getText();
        String middleName = txtMiddleName.getText();
        String lastName = txtLastName.getText();                        
                    
        String mm = txtBirthMonth.getText();
        String dd = txtBirthDay.getText();
        String yyyy = txtBirthYear.getText();
        String birthdayFormat = String.format(mm + "/" + dd + "/" + yyyy);
                    
        String gender = null;
            if (rbtnMale.isSelected()) {
                gender = "Male";
            } else if (rbtnFemale.isSelected()) {
                gender = "Female";
            }
            ButtonGroup gGroup = new ButtonGroup();
            gGroup.add(rbtnMale);
            gGroup.add(rbtnFemale);
                    
        String password = new String(txtPassword.getPassword());
        String conPassword = new String(txtConfirmPassword.getPassword());                 
                 
        
            // Para sure kung may laman yung parts ng form
            if (email.isEmpty() || firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty() || mm.isEmpty() ||
                dd.isEmpty() || yyyy.isEmpty() || password.isEmpty() || conPassword.isEmpty()) {
                JOptionPane.showMessageDialog(btnCreateAccount, "Text fields cannot be empty.");
                return;
            }
                    
            // para sure yung email
            if (!email.endsWith("@gmail.com") && !email.endsWith("@yahoo.com")) {
                JOptionPane.showMessageDialog(btnCreateAccount, "Email must be correct.");
                return;
            }
            
            // Para sure kung hindi sobra or kulang yung numbers sa birthday
            if (!birthdayFormat.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
                JOptionPane.showMessageDialog(btnCreateAccount, "Birthday must be in MM/DD/YYYY format.");
                return;
            }
                    
                    
        // Para sure kung numeric format yung nilagay sa birthday
        LocalDate birthday;
        try {
            birthday = LocalDate.of(Integer.parseInt(yyyy), Integer.parseInt(mm), Integer.parseInt(dd));
        } catch (DateTimeException ex) {
            JOptionPane.showMessageDialog(btnCreateAccount, "Birthday must be correct.");
            return;
        }

        
            // Para sure kung legal age na
            LocalDate present = LocalDate.now();
            int age = present.getYear() - birthday.getYear();
                    
            if (age < 18) {
                JOptionPane.showMessageDialog(btnCreateAccount, "You are not yet in legal age to register.");
                return;
            }
                 
            // Confirm Password
            if (!password.equals(conPassword)) {
                JOptionPane.showMessageDialog(btnCreateAccount, "Passwords do not match.");
                return;
            }

        
        // Check if the full name already exists in the database
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT COUNT(*) FROM login.registration WHERE firstname = ? AND middlename = ? AND lastname = ? AND birthday = ?";
            PreparedStatement pS = connection.prepareStatement(query);
            pS.setString(1, firstName);
            pS.setString(2, middleName);
            pS.setString(3, lastName);
            pS.setString(4, birthdayFormat);
            ResultSet rS = pS.executeQuery();

            if (rS.next() && rS.getInt(1) > 0) {
                JOptionPane.showMessageDialog(btnCreateAccount, "User already exists.");
                return;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(btnCreateAccount, "Database error: " + exception.getMessage());
            return;
        } 
            
        // Para sure kung wala pang katulad yung email sa Database
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT COUNT(*) FROM login.registration WHERE email = ?";
            PreparedStatement pS = connection.prepareStatement(query);
            pS.setString(1, email);
            ResultSet rS = pS.executeQuery();

            if (rS.next() && rS.getInt(1) > 0) {
                JOptionPane.showMessageDialog(btnCreateAccount, "Email already registered.");
                return;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(btnCreateAccount, "Database error: " + exception.getMessage());
            return;
        }
        
        
        // connection sa idGenerator class
        idGenerator idGen = new idGenerator();
        int id = idGen.idGenerator("login", "registration", "voterid");
        
        // Para malagay yung data sa Database
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                        
            String query = "INSERT INTO login.registration (voterid, email, firstname, middlename, lastname,"
                                              + "birthday, gender, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pS = connection.prepareStatement(query);

            pS.setInt(1, id);
            pS.setString(2, email);
            pS.setString(3, firstName);
            pS.setString(4, middleName);
            pS.setString(5, lastName);
            pS.setString(6, birthdayFormat);
            pS.setString(7, gender);
            pS.setString(8, password); 

            int rS = pS.executeUpdate();
            
            txtEmail.setText("");
            txtFirstName.setText("");
            txtMiddleName.setText("");
            txtLastName.setText("");
            txtBirthMonth.setText("");
            txtBirthDay.setText("");
            txtBirthYear.setText("");
            gGroup.remove(rbtnMale);
            gGroup.remove(rbtnFemale);
            rbtnMale.setSelected(false);
            rbtnFemale.setSelected(false);
            txtPassword.setText("");
            txtConfirmPassword.setText("");
            
            if (rS > 0) {
                users_HT.put(email, password);
                JOptionPane.showMessageDialog(btnCreateAccount, "Registartion Successful!");
                dispose();
                new frameLogin().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(btnCreateAccount, "Error creating an account.");
            }
            connection.close();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(btnCreateAccount, "Database error: " + exception.getMessage());
        }
    }
    
    // retrieving data from hash table
    public static Hashtable<String, String> retrieve_HT() {
        return users_HT;
    }
    
    // data sa database will be reloaded in hash table tuwing bubuksan ang app
    public static void data_HT() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT email, password FROM login.registration";
            PreparedStatement pS = connection.prepareStatement(query);

            ResultSet rS = pS.executeQuery();
            while (rS.next()) {
                String email = rS.getString("email");
                String password = rS.getString("password");
                users_HT.put(email, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}