package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class LoginFailurePopupGUI extends JFrame {
    public LoginFailurePopupGUI() {
        setAlwaysOnTop(true);
        setBounds(200, 200, 400, 300);
        JLabel lblNewLabel = new JLabel("invalid username/password combination");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblNewLabel, BorderLayout.CENTER);
        this.setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

}
