package edu.baylor.ecs.csi3471.hotelReservationSystem.backend;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


// I wasn't able to find dependency so I just pulled the actual implementation
// This helps limit character input into JTextFields. (For Card num, cvv, etc.)

// UPDATE (04/24/2023) This might be redundant as NumberOnlyDocument already encapsulates
// feature of limiting number of input digits
public class JTextFieldLimit extends PlainDocument {
    private int limit;

    public JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null)
            return;

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}