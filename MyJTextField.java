import java.awt.Color;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;




public class MyJTextField extends JTextField{
	
    public MyJTextField(int cols) {
        super(cols);
    }

    protected Document createDefaultModel() {
    	getDocument();
        return new UpperCaseDocument();
    }

    static class UpperCaseDocument extends PlainDocument {
    	Random random = new Random();
        public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException {

            if (str == null) {
                return;
            }
            char[] upper = str.toCharArray();
            for (int i = 0; i < upper.length; i++) {
                upper[i] = Character.toUpperCase(upper[i]);
            }
            System.out.println("test "+getLength() +" offs " +offs);
            /*SimpleAttributeSet set = new SimpleAttributeSet();
            // StyleConstants.setBackground(set, new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            StyleConstants.setForeground(set, new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            StyleConstants.setFontSize(set, random.nextInt(12) + 12);
            StyleConstants.setBold(set, random.nextBoolean());
            StyleConstants.setItalic(set, random.nextBoolean());
            StyleConstants.setUnderline(set, random.nextBoolean());

            setCharacterAttributes(offs+1, 1, set, true);*/
            super.insertString(offs, new String(upper), a);
        }
    }


}
