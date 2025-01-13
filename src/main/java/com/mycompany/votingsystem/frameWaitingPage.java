package com.mycompany.votingsystem;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class frameWaitingPage extends JFrame implements ActionListener{

    private final JLabel hdrClosed,lblInfo;
    private final JButton btnSignOut;
    
    public frameWaitingPage() {
        setSize(800,600);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        hdrClosed = new JLabel("The election is yet to be opened.",SwingConstants.CENTER);
        hdrClosed.setFont(new Font("Roboto", Font.BOLD,25));
        //hdrClosed.setBounds(0,200,250, 150);
        hdrClosed.setBounds(0,150,800, 150);
        add(hdrClosed);
        
        lblInfo = new JLabel("Please wait for further announcements of the date.",SwingConstants.CENTER);
        lblInfo.setBounds(0, 250, 800, 75);
        add(lblInfo);
        
        btnSignOut = new JButton("Sign Out");
        btnSignOut.setBounds(600, 500, 100, 30);
        btnSignOut.setFocusable(false);
        add(btnSignOut);
        
       btnSignOut.addActionListener(this);
              
    }   

    @Override
    public void actionPerformed(ActionEvent e) {
        
    if(e.getSource()==btnSignOut){
        dispose();
        new frameLogin().setVisible(true);
    }
        
    }
    
    
}
