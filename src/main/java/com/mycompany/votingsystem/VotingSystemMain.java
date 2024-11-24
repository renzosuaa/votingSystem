package com.mycompany.votingsystem;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



/**
 *
 * @author pc
 */
public class VotingSystemMain {

    public static void main(String[] args) {
        frameWaitingPage fw = new frameWaitingPage();
        frameAdminAccess fa = new frameAdminAccess();
        frameRegistration fr = new frameRegistration();
        fa.setVisible(true);
        }
}
