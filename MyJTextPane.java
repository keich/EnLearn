import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;

import javax.accessibility.AccessibleContext;
import javax.swing.Icon;
import javax.swing.JTextPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.AbstractDocument.DefaultDocumentEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.Position;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class MyJTextPane extends JTextPane {
	//StyledDocument style;
	Random random = new Random();
	
	public MyJTextPane() {
		this.setDocument(new MyStyledDocument());
	}
	
	@Override
	public void replaceSelection(String content) {
		System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
        content = content.replaceAll("\\r", "").replaceAll("\\n", "");
        if(content.length() == 1 ){
			
	        SimpleAttributeSet set = new SimpleAttributeSet();
	        StyleConstants.setForeground(set, new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
	        StyledDocument doc = (StyledDocument) getDocument();

	        int c = getCaretPosition();
	        setCaretPosition(c+1);
	        System.out.println("c = "+c + " and content @"+content+"@"+content.length());
	        doc.setCharacterAttributes(c, 1, set, true);
       }
		//super.replaceSelection(content);
	}

	@Override
	public Style addStyle(String nm, Style parent) {
		System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
		return super.addStyle(nm, parent);
	}

	@Override
	protected EditorKit createDefaultEditorKit() {
		System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
		return super.createDefaultEditorKit();
	}

	@Override
	public AttributeSet getCharacterAttributes() {
		System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
		return super.getCharacterAttributes();
	}

	@Override
	public MutableAttributeSet getInputAttributes() {
		System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
		return super.getInputAttributes();
	}

	@Override
	public Style getLogicalStyle() {
		System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
		return super.getLogicalStyle();
	}

	@Override
	public AttributeSet getParagraphAttributes() {
		System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
		return super.getParagraphAttributes();
	}

	@Override
	public Style getStyle(String nm) {
		System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
		return super.getStyle(nm);
	}

	@Override
	public StyledDocument getStyledDocument() {
		System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
		return super.getStyledDocument();
	}

	@Override
	public String getUIClassID() {
		System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
		return super.getUIClassID();
	}

	@Override
	public void insertComponent(Component c) {
		System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
		super.insertComponent(c);
	}

	@Override
	public void insertIcon(Icon g) {
		System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
		super.insertIcon(g);
	}

	@Override
	protected String paramString() {
		System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
		return super.paramString();
	}

	@Override
	public void removeStyle(String nm) {
		System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
		super.removeStyle(nm);
	}

	@Override
	public void setCharacterAttributes(AttributeSet attr, boolean replace) {
		System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
		super.setCharacterAttributes(attr, replace);
	}

	@Override
	public void setDocument(Document doc) {
		System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
		super.setDocument(doc);
	}

	@Override
	public void setLogicalStyle(Style s) {
		System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
		super.setLogicalStyle(s);
	}

	@Override
	public void setParagraphAttributes(AttributeSet attr, boolean replace) {
		System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
		super.setParagraphAttributes(attr, replace);
	}

	@Override
	public void setStyledDocument(StyledDocument doc) {
		System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
		super.setStyledDocument(doc);
	}

	public class MyStyledDocument extends DefaultStyledDocument {
		Random random = new Random();
		
		@Override
		public void setCharacterAttributes(int offset, int length,
				AttributeSet s, boolean replace) {
			System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
			super.setCharacterAttributes(offset, length, s, replace);
		}

		@Override
		protected void insertUpdate(DefaultDocumentEvent chng, AttributeSet attr) {
			System.out.println("MyStyledDocument " + new Object(){}.getClass().getEnclosingMethod().getName());
			super.insertUpdate(chng, attr);
		}

		@Override
		protected void removeUpdate(DefaultDocumentEvent chng) {
			System.out.println("MyStyledDocument " + new Object(){}.getClass().getEnclosingMethod().getName());

			super.removeUpdate(chng);
		}
		
	}

}
