/*
 * A class that displays a pop-up when login failure occurs.
 */
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

        setSize(400, 300);
        setLocationRelativeTo(null);
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
