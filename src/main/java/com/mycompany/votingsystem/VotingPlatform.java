/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.votingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.awt.image.ImageObserver.HEIGHT;

public class VotingPlatform extends JFrame{
    
    private JLabel lblcandidatename,lblcandidateid,lblpres,lblVpres,lblsenators;
    private JScrollPane mainscrollpane,innerscrollpane1,innerscrollpane2,innerscrollpane3;
    private JPanel mainpanel;
    private JButton btnsubmit,btnsignout;

    
    VotingPlatform(){
    
       
        
            setSize(800,600);
            setLayout(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);
            setTitle("Voting Selection ");
            
            mainpanel = new JPanel();
            mainpanel.setLayout(null);
            mainpanel.setBackground(Color.LIGHT_GRAY);
            
            lblpres = new JLabel("President");
            lblpres.setFont(new Font("Arial",Font.BOLD,15));
            lblpres.setBounds(30,20,200,30);
            mainpanel.add(lblpres);
            
            lblVpres = new JLabel("Vice President ");
            lblVpres.setFont(new Font("Arial",Font.BOLD,15));
            lblVpres.setBounds(30,135,200,30);
           
            mainpanel.add(lblVpres);
            
            lblsenators = new JLabel("Senators ");
            lblsenators.setFont(new Font("Arial",Font.BOLD,15));
            lblsenators.setBounds(30,260,200,30);
            
            mainpanel.add(lblsenators);
           
            //scrollpane
            
            innerscrollpane1 = new JScrollPane(mainscrollpane);
            innerscrollpane1.setBounds(100,156,550,70);
            add(innerscrollpane1);
            
            innerscrollpane2 = new JScrollPane(mainscrollpane);
            innerscrollpane2.setBounds(100,268,550,80);
            add(innerscrollpane2);
            
            innerscrollpane3 = new JScrollPane(mainscrollpane);
            innerscrollpane3.setBounds(100,390,553,85);
            add(innerscrollpane3);
            
            
            mainscrollpane = new JScrollPane(mainpanel);
            mainscrollpane.setBounds(50,100, 680, 400);
            //mainscrollpane.setBackground(Color.GRAY);
            //mainscrollpane.getViewport().setBackground(Color.LIGHT_GRAY);
            add(mainscrollpane);
            
            
            
            
            
            
            
            lblcandidatename = new JLabel("Candidates Name");
            lblcandidatename.setFont(new Font("Arial",Font.BOLD,25));
            lblcandidatename.setBounds(30,30,700,30);
            add(lblcandidatename);
            
            lblcandidateid = new JLabel("Candidate_ID");
            lblcandidateid.setFont(new Font("Arial",Font.BOLD,15));
            lblcandidateid.setBounds(40,60,700,30);
            add(lblcandidateid);
            
            //button
            
            
            btnsubmit = new JButton("SUBMIT");
             btnsubmit.setBounds(450,515,120,30);
            add( btnsubmit);
            
             btnsignout = new JButton("SIGN OUT");
             btnsignout.setBounds(600,515,110,30);
            add( btnsignout);
           
            //actionlistener for buttons
           
           
            this. btnsubmit.addActionListener(new ActionListener(){
                @Override
                    public void actionPerformed(ActionEvent e){
                    
                    if(e.getSource()==  btnsubmit){
                        JOptionPane.showMessageDialog(null,"Your Vote is already submitted !","Voting Platform",JOptionPane.INFORMATION_MESSAGE);
                    }else if(e.getSource()==  btnsignout){
                            JOptionPane.showMessageDialog(null,"Thankyou for Voting !","Voting Platform", HEIGHT);
                            }
                    }
            
            });
       
           
           
            
            setVisible(true);
    }
    
}
