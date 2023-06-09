/*
 * A class that generates a pop-up window. 
 * It extends JFrame to be displayed on screen
 */
package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import javax.swing.*;
import java.awt.*;

public class PopUpWindows extends JFrame {

    private final String text;

    public PopUpWindows(String text) throws HeadlessException {
        this.text = text;
    }

    public void printPopUp(){
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16 ));
        JOptionPane.showOptionDialog(null, label, null, 0, -1, null, new Object[]{}, null);
    }
}
