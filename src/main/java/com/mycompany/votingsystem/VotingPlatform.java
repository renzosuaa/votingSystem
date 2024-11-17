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
    
    private JLabel candidatename,candidateid,pres,Vpres,senators;
    private JScrollPane mainscrollpane,innerscrollpane1,innerscrollpane2,innerscrollpane3;
    private JPanel mainpanel;
    private JButton submit,signout;

    
    VotingPlatform(){
    
       
        
            setSize(1200,700);
            setLayout(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);
            setTitle("Voting Selection ");
            
            mainpanel = new JPanel();
            mainpanel.setLayout(null);
            mainpanel.setBackground(Color.LIGHT_GRAY);
            
            pres = new JLabel("President");
            pres.setFont(new Font("Arial",Font.BOLD,15));
            pres.setBounds(50,30,200,30);
            mainpanel.add(pres);
            
            Vpres = new JLabel("Vice President ");
            Vpres.setFont(new Font("Arial",Font.BOLD,15));
            Vpres.setBounds(50,150,200,30);
           
            mainpanel.add(Vpres);
            
            senators = new JLabel("Senators ");
            senators.setFont(new Font("Arial",Font.BOLD,15));
            senators.setBounds(50,280,200,30);
            
            mainpanel.add(senators);
           
            //scrollpane
            
            innerscrollpane1 = new JScrollPane(mainscrollpane);
            innerscrollpane1.setBounds(100,170,900,70);
            add(innerscrollpane1);
            
            innerscrollpane2 = new JScrollPane(mainscrollpane);
            innerscrollpane2.setBounds(100,290,900,80);
            add(innerscrollpane2);
            
            innerscrollpane3 = new JScrollPane(mainscrollpane);
            innerscrollpane3.setBounds(100,420,900,150);
            add(innerscrollpane3);
            
            
            mainscrollpane = new JScrollPane(mainpanel);
            mainscrollpane.setBounds(50,100, 1050, 500);
            //mainscrollpane.setBackground(Color.GRAY);
            //mainscrollpane.getViewport().setBackground(Color.LIGHT_GRAY);
            add(mainscrollpane);
            
            
            
            
            
            
            
            candidatename = new JLabel("Candidates Name");
            candidatename.setFont(new Font("Arial",Font.BOLD,25));
            candidatename.setBounds(30,30,700,30);
            add(candidatename);
            
            candidateid = new JLabel("Candidate_ID");
            candidateid.setFont(new Font("Arial",Font.BOLD,15));
            candidateid.setBounds(40,60,700,30);
            add(candidateid);
            
            //button
            
            
            submit = new JButton("SUBMIT");
            submit.setBounds(700,610,120,30);
            add(submit);
            
            signout = new JButton("SIGN OUT");
            signout.setBounds(900,610,110,30);
            add(signout);
           
            //actionlistener for buttons
           
           
            this.submit.addActionListener(new ActionListener(){
                @Override
                    public void actionPerformed(ActionEvent e){
                    
                    if(e.getSource()== submit){
                        JOptionPane.showMessageDialog(null,"Your Vote is already submitted !","Voting Platform",JOptionPane.INFORMATION_MESSAGE);
                    }else if(e.getSource()== signout){
                            JOptionPane.showMessageDialog(null,"Thankyou for Voting !","Voting Platform", HEIGHT);
                            }
                    }
            
            });
       
           
           
            
            setVisible(true);
    }
    
}
