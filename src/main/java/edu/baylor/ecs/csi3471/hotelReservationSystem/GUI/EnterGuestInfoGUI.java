/**
 * file: EnterGuestInfoGUI.java
 * author: KayLynn Beard
 *
 * UI for clerks to get guest information (search for existing
 * guest or create account using UserProfileGUI) so they can
 * make reservations on behalf of guest
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
    private JDialog dialog;
    private Guest g;

    private JPanel createNewGuestPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton submit = new JButton("   Create New Guest   ");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserProfileGUI u = new UserProfileGUI(new Guest());
                g = (Guest) u.getMyUser();
                if(u.isSuccessful()){
                    createConfirmationDialog();
                }
            }
        });
        panel.add(new JLabel("Or create new guest account:  "));
        panel.add(submit);
        return panel;
    }

    private void createConfirmationDialog() {
        JDialog foundDialog = new JDialog();
        foundDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        foundDialog.setSize(200, 150);
        foundDialog.setTitle("Guest Found!");
        foundDialog.add(displayFoundGuest());
        JButton confirm = new JButton("Confirm");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                foundDialog.dispose();
                dialog.dispose();
            }
        });
        foundDialog.add(confirm, BorderLayout.SOUTH);
        foundDialog.setVisible(true);
    }

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
                    g = (Guest)Hotel.searchForAccountByUsername(searchField.getText());
                    if(g == null){
                        JOptionPane.showMessageDialog(null, "Guest not found. Try again or " +
                                "create new guest.");
                    } else{
                        createConfirmationDialog();
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
        return panel;
    }

    private JPanel displayFoundGuest(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(new JLabel(" Confirm Guest Information:"));
        panel.add(new JLabel(" ---------------------------------------------"));
        panel.add(new JLabel(" First Name: " + g.getNameFirst()));
        panel.add(new JLabel(" Last Name: " + g.getNameLast()));
        panel.add(new JLabel(" Username: " + g.getAccountUsername()));
        JLabel label = new JLabel();
        if(g.corporate()){
            label.setText("Corporate: YES");
        } else{
            label.setText("Corporate: NO");
        }
        return panel;
    }

    public EnterGuestInfoGUI(){
        dialog = new JDialog(dialog, "Enter Guest Information", Dialog.ModalityType.DOCUMENT_MODAL);
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setSize(360, 200);
        dialog.setLayout(new BorderLayout());

        dialog.add(createSearchPanel(), BorderLayout.NORTH);
        dialog.add(createNewGuestPanel(), BorderLayout.CENTER);

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
