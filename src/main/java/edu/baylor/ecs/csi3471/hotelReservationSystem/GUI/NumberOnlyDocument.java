//This is Document Formatting class for limiting textfield input to number only.
//I added this because I don't know how to use FormattedText and its much easier to
//Implement this and go along with the TextFieldLimit.

package edu.baylor.ecs.csi3471.hotelReservationSystem.GUI;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class NumberOnlyDocument extends PlainDocument {
    private int limit;

    public NumberOnlyDocument(int limit) {
        super();
        this.limit = limit;
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }

        if ((getLength() + str.length()) <= limit) {
            for (int i = 0; i < str.length(); i++) {
                if (!Character.isDigit(str.charAt(i))) {
                    return;
                }
            }
            super.insertString(offset, str, attr);
        }
    }
}







