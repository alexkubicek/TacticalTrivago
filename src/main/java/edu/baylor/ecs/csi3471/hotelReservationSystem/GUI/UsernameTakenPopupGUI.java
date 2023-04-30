/*
 * GUI that creates a pop-up to display an error when a user attempts to
 * create an account with a taken username.
 */
package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class UsernameTakenPopupGUI extends JFrame {
    public UsernameTakenPopupGUI() {

        JLabel textLabel = new JLabel("Username is already taken, please try a different username.");
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(textLabel, BorderLayout.CENTER);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

}

