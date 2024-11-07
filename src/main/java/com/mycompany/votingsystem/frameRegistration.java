package com.mycompany.votingsystem;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.LocalDate;
import java.time.Period;

public class frameRegistration extends JFrame {

    // UI components
    private JLabel labelHeader, labelEmail, labelFirstName, labelMiddleName, labelLastName, labelBirthday, labelGender, labelPassword, labelConfirmPassword;
    private JTextField txtEmail, txtFirstName, txtMiddleName, txtLastName, txtBirthMonth, txtBirthDay, txtBirthYear;
    private JPasswordField txtPassword, txtConfirmPassword;
    private JRadioButton rbtnMale, rbtnFemale;
    private JButton btnCreateAccount;

    frameRegistration() {
        
        setTitle("Registration");
        setSize(800, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        labelHeader = new JLabel("Welcome! Please Register", SwingConstants.CENTER);
        labelHeader.setBounds(150, 50, 500, 50);
        add(labelHeader);

        // Email
        labelEmail = new JLabel("Email:");
        labelEmail.setBounds(150, 120, 150, 30);
        add(labelEmail);
        txtEmail = new JTextField();
        txtEmail.setBounds(300, 120, 250, 30);
        add(txtEmail);

        // First Name
        labelFirstName = new JLabel("First Name:");
        labelFirstName.setBounds(150, 170, 150, 30);
        add(labelFirstName);
        txtFirstName = new JTextField();
        txtFirstName.setBounds(300, 170, 250, 30);
        add(txtFirstName);

        // Middle Name
        labelMiddleName = new JLabel("Middle Name:");
        labelMiddleName.setBounds(150, 220, 150, 30);
        add(labelMiddleName);
        txtMiddleName = new JTextField();
        txtMiddleName.setBounds(300, 220, 250, 30);
        add(txtMiddleName);

        // Last Name
        labelLastName = new JLabel("Last Name:");
        labelLastName.setBounds(150, 270, 150, 30);
        add(labelLastName);
        txtLastName = new JTextField();
        txtLastName.setBounds(300, 270, 250, 30);
        add(txtLastName);

        // Birthday
        labelBirthday = new JLabel("Birthday (MM/DD/YYYY):");
        labelBirthday.setBounds(150, 320, 150, 30);
        add(labelBirthday);
        
        txtBirthMonth = new JTextField();
        txtBirthMonth.setBounds(300, 320, 50, 30);
        add(txtBirthMonth);

        txtBirthDay = new JTextField();
        txtBirthDay.setBounds(360, 320, 50, 30);
        add(txtBirthDay);

        txtBirthYear = new JTextField();
        txtBirthYear.setBounds(420, 320, 80, 30);
        add(txtBirthYear);

        // Gender
        labelGender = new JLabel("Gender:");
        labelGender.setBounds(150, 370, 150, 30);
        add(labelGender);
        
        rbtnMale = new JRadioButton("Male");
        rbtnMale.setBounds(300, 370, 100, 30);
        add(rbtnMale);
        
        rbtnFemale = new JRadioButton("Female");
        rbtnFemale.setBounds(410, 370, 100, 30);
        add(rbtnFemale);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbtnMale);
        genderGroup.add(rbtnFemale);

        // Password
        labelPassword = new JLabel("Password:");
        labelPassword.setBounds(150, 420, 150, 30);
        add(labelPassword);
        txtPassword = new JPasswordField();
        txtPassword.setBounds(300, 420, 250, 30);
        add(txtPassword);

        // Confirm Password
        labelConfirmPassword = new JLabel("Confirm Password:");
        labelConfirmPassword.setBounds(150, 470, 150, 30);
        add(labelConfirmPassword);
        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setBounds(300, 470, 250, 30);
        add(txtConfirmPassword);

        // Create Account Button
        btnCreateAccount = new JButton("Create Account");
        btnCreateAccount.setBounds(320, 520, 200, 30);
        add(btnCreateAccount);

        btnCreateAccount.addActionListener(this::onCreateAccount);
    }

    private void onCreateAccount(ActionEvent e) {
        String firstName = txtFirstName.getText().trim();
        String middleName = txtMiddleName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String email = txtEmail.getText().trim();
        String password = new String(txtPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int birthMonth, birthDay, birthYear;
        try {
            birthMonth = Integer.parseInt(txtBirthMonth.getText().trim());
            birthDay = Integer.parseInt(txtBirthDay.getText().trim());
            birthYear = Integer.parseInt(txtBirthYear.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate, currentDate).getYears();

        if (age < 18) {
            JOptionPane.showMessageDialog(this, "You must be at least 18 years old to register.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String gender = rbtnMale.isSelected()? "Male": "Female";
        JOptionPane.showMessageDialog(this, "Registration Successful", "Success", JOptionPane.INFORMATION_MESSAGE);

    }
}