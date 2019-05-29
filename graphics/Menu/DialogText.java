package graphics.Menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import graphics.shapes.SText;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.Editor;

public class DialogText extends Dialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private static final Dimension DEFAULT_DIMENSION_PANEL = new Dimension(250,60);
	private static final Dimension DEFAULT_DIMENSION_TEXT_FIELD = new Dimension(60,25);
	private static final String DEFAULT_TITLE = "Créer un nouveau text";
	private static final String DEFAULT_TEXT= "Hello";
	private static final String TEXT = "Text";
	private static final String TEXT_LABEL = "text : ";
	private static final String FONT = "Font";
	private static final String FONT_LABEL = "font : ";
	private static final String SIZE_LABEL = "size : ";
	private static final String STYLE_LABEL = "style : ";
	private static final String MONACO = "Monaco";
	private static final String COURIER_NEW = "Courier New";
	private static final String PRAGMATA = "Pragmata";
	private static final String ARIAL = "Arial";
	private static final String BOLD = "Bold";
	private static final String ITALIC = "Italic";
	private static final String PLAIN = "Plain";
	private static final int DEFAULT_SIZE = 30;

	private JTextField text;
	private JComboBox<String> font;
	private JTextField size;
	private JComboBox<String> style;

	
	public DialogText() {
		
		super.setTitle(DEFAULT_TITLE);
		
		JPanel panelPosition = super.panelPosition();	
		JPanel panelColor = super.panelColor();
		JPanel panelFilled = super.panelFilled();
		JPanel panelStrocked = super.panelStrocked();
		JPanel panelValidation = super.panelValidation();
		JPanel panelText = this.panelText();
		JPanel panelFont = this.panelFont();
		JPanel panelSettings = super.panelSettings(panelPosition, panelFilled, panelStrocked, panelColor);
		panelSettings.add(panelText);
		panelSettings.add(panelFont);
	
	    this.getContentPane().add(panelSettings, BorderLayout.CENTER);
	    this.getContentPane().add(panelValidation, BorderLayout.SOUTH);
	    
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(super.getOk())) {
			SText t = new SText(new Point(getX(),getY()),this.getText());
			t.addAttributes(new ColorAttributes(super.getfTrue(),super.getsTrue(),super.getFilledColor(),super.getStrockedColor()));
			t.addAttributes(new SelectionAttributes());
			t.addAttributes(new FontAttributes(this.getF()));
			Editor.model.add(t);
			Editor.sview.repaint();
			super.setVisible(false);
		}
	}
	
	public JPanel panelText() {
		JPanel panelText = new JPanel();
		panelText.setBorder(BorderFactory.createTitledBorder(TEXT));
		panelText.setPreferredSize(DEFAULT_DIMENSION_PANEL);
		JLabel textLabel = new JLabel(TEXT_LABEL);
		panelText.add(textLabel);
		this.text = new JTextField(DEFAULT_TEXT);
		this.text.setPreferredSize(DEFAULT_DIMENSION_TEXT_FIELD);
		panelText.add(this.text);
		
		return panelText;
	}
	
	public JPanel panelFont() {
		JPanel panelFont = new JPanel();
		panelFont.setBorder(BorderFactory.createTitledBorder(FONT));
		panelFont.setPreferredSize(new Dimension(450,70));
		JLabel fontLabel = new JLabel(FONT_LABEL);
		this.font = new JComboBox<String>();
		font.addItem(MONACO);
		font.addItem(COURIER_NEW);
		font.addItem(PRAGMATA);
		font.addItem(ARIAL);
		panelFont.add(fontLabel);
		panelFont.add(font);
		JLabel sizeLabel = new JLabel(SIZE_LABEL);
		panelFont.add(sizeLabel);
		this.size = new JTextField(Integer.toString(DEFAULT_SIZE));
		this.size.setPreferredSize(DEFAULT_DIMENSION_TEXT_FIELD);
		panelFont.add(size);
		JLabel styleLabel = new JLabel(STYLE_LABEL);
		this.style = new JComboBox<String>();
		style.addItem(BOLD);
		style.addItem(ITALIC);
		style.addItem(PLAIN);
		panelFont.add(styleLabel);
		panelFont.add(style);
		return panelFont;
	}
	
	public String getText() {
		return this.text.getText();
	}
	
	public int getStyle() {
		switch ((String) this.style.getSelectedItem()) {
			case ITALIC:
		      return Font.ITALIC;
			case PLAIN:
			   return Font.PLAIN;
		}
		return Font.BOLD;
	}
	
	public Font getF() {
		Font font = new Font((String) this.font.getSelectedItem(),this.getStyle(),Integer.parseInt(this.size.getText()));
		return font;
	}
	
}