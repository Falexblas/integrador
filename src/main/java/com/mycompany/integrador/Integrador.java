package com.mycompany.integrador;

import Vista.login;
import javax.swing.JFrame;

public class Integrador {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        login panelLogin = new login();  // tu JPanel
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panelLogin);
        frame.pack();                     
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }
}
