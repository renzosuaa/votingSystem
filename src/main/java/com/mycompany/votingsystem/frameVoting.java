/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.votingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.awt.image.ImageObserver.HEIGHT;

public class frameVoting extends JFrame implements ActionListener {
    
    private JLabel lblCandidateName,lblCandidateId,lblPres,lblVpres,lblSenators;
    private JScrollPane MainScrollPane,InnerScrollPane1,InnerScrollPane2,InnerScrollPane3;
    private JPanel Mainpanel;
    private JButton btnSubmit,btnSignOut;

    
    frameVoting(){
    
       
        
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
            
            InnerScrollPane1 = new JScrollPane(MainScrollPane);
            InnerScrollPane1.setBounds(100,156,550,70);
            add(InnerScrollPane1);
            
            InnerScrollPane2 = new JScrollPane(MainScrollPane);
            InnerScrollPane2.setBounds(100,268,550,80);
            add(InnerScrollPane2);
            
            InnerScrollPane3 = new JScrollPane(MainScrollPane);
            InnerScrollPane3.setBounds(100,390,553,85);
            add(InnerScrollPane3);
            
            
            MainScrollPane = new JScrollPane(Mainpanel);
            MainScrollPane.setBounds(50,100, 680, 400);
            //mainscrollpane.setBackground(Color.GRAY);
            //mainscrollpane.getViewport().setBackground(Color.LIGHT_GRAY);
            add(MainScrollPane);
            
            
            
            
            
            
            
            lblCandidateName = new JLabel("Candidates Name");
            lblCandidateName.setFont(new Font("Arial",Font.BOLD,25));
            lblCandidateName.setBounds(30,30,700,30);
            add(lblCandidateName);
            
            lblCandidateId = new JLabel("Candidate_ID");
            lblCandidateId.setFont(new Font("Arial",Font.BOLD,15));
            lblCandidateId.setBounds(40,60,700,30);
            add(lblCandidateId);
            
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
            new frameLogin().setVisible(true);
        }
    }
    
    
    
}
