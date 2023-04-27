/**
 * file: EnterGuestInfoGUI.java
 * author: KayLynn Beard
 *
 * UI for clerks to enter guest information to make/edit
 * reservations on behalf of guest
 *
 * USAGE: EnterGuestInfoGUI g = new EnterGuestInfoGUI();
 *        Guest guest = g.getGuest();
 */

package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Guest;
import edu.baylor.ecs.csi3471.hotelReservationSystem.backend.Hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EnterGuestInfoGUI {
    private JButton submit;
    private JDialog dialog;
    JTextField firstNameField = new JTextField("", 20);
    JTextField lastNameField = new JTextField("", 20);
    private Guest g;

    private JPanel createSearchPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setPreferredSize(new Dimension(360, 80));

        JLabel searchLabel = new JLabel("Search for existing guest account by username: ");
        JTextField searchField = new JTextField("", 25);
        searchField.setSize(new Dimension(30, 15));
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Guest g = (Guest)Hotel.searchForAccountByUsername(searchField.getText());
                    if(g == null){
                        JOptionPane.showMessageDialog(null, "Guest not found. Try again or " +
                                "enter new guest information manually.");
                    } else{
                        firstNameField.setText(g.getNameFirst());
                        lastNameField.setText(g.getNameLast());
                        panel.add(new JLabel("Guest found!"));
                    }
                } catch(ClassCastException ex){
                    JOptionPane.showMessageDialog(null, "Username matches clerk/admin " +
                            "account. Please enter guest usernames only.");
                }
            }
        });

        panel.add(searchLabel);
        panel.add(searchField);
        panel.add(searchButton);
        panel.add(new JLabel("Manually enter new guest information:"));
        return panel;
    }

    private JPanel createFieldList(){
        JPanel panel = new JPanel();
        JLabel firstNameLabel = new JLabel("First Name: ");
        JLabel lastNameLabel = new JLabel("Last Name: ");
        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        return panel;
    }

    public EnterGuestInfoGUI(){
        dialog = new JDialog(dialog, "Enter Guest Information", Dialog.ModalityType.DOCUMENT_MODAL);
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setSize(360, 210);
        dialog.setLayout(new BorderLayout());

        submit = new JButton("Create New Guest");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(firstNameField.getText().equals("") || lastNameField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please fill in both name fields.");
                } else{
                    g = new Guest(firstNameField.getText(), lastNameField.getText(), null);
                    dialog.dispose();
                }
            }
        });

        dialog.add(createSearchPanel(), BorderLayout.NORTH);
        dialog.add(createFieldList(), BorderLayout.CENTER);
        dialog.add(submit, BorderLayout.SOUTH);

        dialog.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null,
                        "Would you like to cancel this session and return to main menu?");
                if(result == JOptionPane.OK_OPTION) {
                    dialog.dispose();
                }
            }
        });

        dialog.setVisible(true);
    }

    public Guest getGuest(){
        return g;
    }

}
