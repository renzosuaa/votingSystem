package com.mycompany.votingsystem;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.LocalDate;
import java.time.Period;
import javax.swing.SwingConstants;

    // MARC LAURENCE A. LUNA
public class frameRegistration extends JFrame {

    // UI components
    private JLabel labelHeader,labelLogo, labelCommission, labelIbits, labelEmail, labelFirstName, labelMiddleName, labelLastName, labelBirthday, labelGender, labelPassword, labelConfirmPassword;
    private JTextField txtEmail, txtFirstName, txtMiddleName, txtLastName, txtBirthMonth, txtBirthDay, txtBirthYear;
    private JPasswordField txtPassword, txtConfirmPassword;
    private JRadioButton rbtnMale, rbtnFemale;
    private JButton btnCreateAccount, btnCancel;

    frameRegistration() {
        
        setTitle("Registration");
        setSize(800, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        labelHeader = new JLabel("Welcome! Please Register", SwingConstants.LEFT);
        labelHeader.setBounds(170, 20, 300, 50);
        labelHeader.setFont(new Font ("Arial",Font.BOLD,20));
        add(labelHeader);
        
        // Logo
        labelLogo = new JLabel();
        labelLogo.setBounds(525, 50, 200,350); 
        ImageIcon logoIcon = new ImageIcon("Commission on Elections.png");
        labelLogo.setIcon(new ImageIcon(logoIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        add(labelLogo);
        
        //Label Logoname    
        labelCommission = new JLabel("COMMISION ON ELECTIONS");
        labelCommission.setBounds(515, 50, 400, 600);
        labelCommission.setFont(new Font ("Arial",Font.BOLD,16));
        add(labelCommission);
        
        //label logoname
        labelIbits = new JLabel("IBITS-BC");
        labelIbits.setBounds(585, 50, 400, 650);
        labelIbits.setFont(new Font ("Arial",Font.BOLD,16));
        add(labelIbits);
        

        // Email
        labelEmail = new JLabel("Email:");
        labelEmail.setBounds(30, 90, 150, 30);
        add(labelEmail);
        txtEmail = new JTextField();
        txtEmail.setBounds(170, 90, 300, 30);
        add(txtEmail);

        // First Name
        labelFirstName = new JLabel("First Name:");
        labelFirstName.setBounds(30, 130, 150, 30);
        add(labelFirstName);
        txtFirstName = new JTextField();
        txtFirstName.setBounds(170, 130, 300, 30);
        add(txtFirstName);

        // Middle Name
        labelMiddleName = new JLabel("Middle Name:");
        labelMiddleName.setBounds(30, 170, 150, 30);
        add(labelMiddleName);
        txtMiddleName = new JTextField();
        txtMiddleName.setBounds(170, 170, 300, 30);
        add(txtMiddleName);

        // Last Name
        labelLastName = new JLabel("Last Name:");
        labelLastName.setBounds(30, 210, 150, 30);
        add(labelLastName);
        txtLastName = new JTextField();
        txtLastName.setBounds(170, 210, 300, 30);
        add(txtLastName);

        // Birthday
        labelBirthday = new JLabel("Birthday (MM/DD/YYYY):");
        labelBirthday.setBounds(30, 250, 150, 30);
        add(labelBirthday);
        
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
        labelGender = new JLabel("Gender:");
        labelGender.setBounds(30, 290, 150, 30);
        add(labelGender);
        
        rbtnMale = new JRadioButton("Male");
        rbtnMale.setBounds(170, 290, 100, 30);
        add(rbtnMale);
        
        rbtnFemale = new JRadioButton("Female");
        rbtnFemale.setBounds(280, 290, 100, 30);
        add(rbtnFemale);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rbtnMale);
        genderGroup.add(rbtnFemale);

        // Password
        labelPassword = new JLabel("Password:");
        labelPassword.setBounds(30, 330, 150, 30);
        add(labelPassword);
        txtPassword = new JPasswordField();
        txtPassword.setBounds(170, 330, 300, 30);
        add(txtPassword);

        // Confirm Password
        labelConfirmPassword = new JLabel("Confirm Password:");
        labelConfirmPassword.setBounds(30, 370, 150, 30);
        add(labelConfirmPassword);
        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setBounds(170, 370, 300, 30);
        add(txtConfirmPassword);

        // Create Account Button
        btnCreateAccount = new JButton("Create Account");
        btnCreateAccount.setBounds(180, 420, 130, 30);
        add(btnCreateAccount);
        
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(320, 420, 130, 30);
        add(btnCancel);

        btnCreateAccount.addActionListener(this::onCreateAccount);
        btnCancel.addActionListener(e -> {
            dispose(); // Close the registration frame
            JOptionPane.showMessageDialog(this, "Returning to Login Page", "Info", JOptionPane.INFORMATION_MESSAGE);
            new frameLogin().setVisible(true);
 
        });
    }
    // function
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