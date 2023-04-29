package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.Timer;

public class LoginFailurePopupGUI extends JFrame {
    public LoginFailurePopupGUI() {
        setAlwaysOnTop(true);
        setBounds(400, 200, 400, 300);
        JLabel lblNewLabel = new JLabel("Invalid username/password combination");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblNewLabel, BorderLayout.CENTER);
        setTitle("Login Failed");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        
        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
        
        setVisible(true);
    }
}
