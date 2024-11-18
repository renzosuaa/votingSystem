/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.votingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.awt.image.ImageObserver.HEIGHT;

public class VotingPlatform extends JFrame implements ActionListener {
    
    private JLabel lblCandidatename,lblCandidateid,lblPres,lblVpres,lblSenators;
    private JScrollPane Mainscrollpane,Innerscrollpane1,Innerscrollpane2,Innerscrollpane3;
    private JPanel Mainpanel;
    private JButton btnSubmit,btnSignOut;

    
    VotingPlatform(){
    
       
        
            setSize(800,600);
            setLayout(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);
            setTitle("Voting Selection ");
            
            Mainpanel = new JPanel();
            Mainpanel.setLayout(null);
            Mainpanel.setBackground(Color.LIGHT_GRAY);
            
            lblPres = new JLabel("President");
            lblPres.setFont(new Font("Arial",Font.BOLD,15));
            lblPres.setBounds(30,20,200,30);
            Mainpanel.add(lblPres);
            
            lblVpres = new JLabel("Vice President ");
            lblVpres.setFont(new Font("Arial",Font.BOLD,15));
            lblVpres.setBounds(30,135,200,30);
           
            Mainpanel.add(lblVpres);
            
            lblSenators = new JLabel("Senators ");
            lblSenators.setFont(new Font("Arial",Font.BOLD,15));
            lblSenators.setBounds(30,260,200,30);
            
            Mainpanel.add(lblSenators);
           
            //scrollpane
            
            Innerscrollpane1 = new JScrollPane(Mainscrollpane);
            Innerscrollpane1.setBounds(100,156,550,70);
            add(Innerscrollpane1);
            
            Innerscrollpane2 = new JScrollPane(Mainscrollpane);
            Innerscrollpane2.setBounds(100,268,550,80);
            add(Innerscrollpane2);
            
            Innerscrollpane3 = new JScrollPane(Mainscrollpane);
            Innerscrollpane3.setBounds(100,390,553,85);
            add(Innerscrollpane3);
            
            
            Mainscrollpane = new JScrollPane(Mainpanel);
            Mainscrollpane.setBounds(50,100, 680, 400);
            //mainscrollpane.setBackground(Color.GRAY);
            //mainscrollpane.getViewport().setBackground(Color.LIGHT_GRAY);
            add(Mainscrollpane);
            
            
            
            
            
            
            
            lblCandidatename = new JLabel("Candidates Name");
            lblCandidatename.setFont(new Font("Arial",Font.BOLD,25));
            lblCandidatename.setBounds(30,30,700,30);
            add(lblCandidatename);
            
            lblCandidatename = new JLabel("Candidate_ID");
            lblCandidatename.setFont(new Font("Arial",Font.BOLD,15));
            lblCandidatename.setBounds(40,60,700,30);
            add(lblCandidatename);
            
            //button
            
            
            btnSubmit = new JButton("SUBMIT");
             btnSubmit.setBounds(450,515,120,30);
            add( btnSubmit);
            
             btnSignOut = new JButton("SIGN OUT");
             btnSignOut.setBounds(600,515,110,30);
            add( btnSignOut);
           
            //actionlistener for buttons
           
          
            btnSubmit.addActionListener(this);
            btnSignOut.addActionListener(this);
    
   
            setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
             if (e.getSource() == btnSubmit) {
            JOptionPane.showMessageDialog(this, "Your Vote is Already Submitted, Thank You !", "Voting Guide", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else if (e.getSource() == btnSignOut) {
            JOptionPane.showMessageDialog(this, "Thank You for Voting Wisely !", "Voting Guide", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }
    
    
    
}
