package graphics.Menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Dialog extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_VALUE=100;
	
	private static final Dimension DEFAULT_DIMENSION_PANEL = new Dimension(250,60);
	private static final Dimension DEFAULT_DIMENSION_TEXT_FIELD = new Dimension(60,25);
	private static final String RED = "Red";
	private static final String BLUE = "Blue";
	private static final String YELLOW = "Yellow";
	private static final String BLACK = "Black";
	private static final String MAGENTA = "Magenta";
	private static final String POSITION = "Position";
	private static final String X_LABEL = "x : ";
	private static final String Y_LABEL = "y : ";
	private static final String COLOR = "Color";
	private static final String STROKED_COLOR_LABEL = "strocked color : ";
	private static final String FILLED_COLOR_LABEL = "filled color : ";
	private static final String STROKED_LABEL = "strocked : ";
	private static final String FILLED_LABEL = "filled : ";
	private static final String STROKED = "Strocked";
	private static final String FILLED = "Filled";
	private static final String TRUE = "True";
	private static final String FALSE = "False";
	private static final String OK = "OK";
	
	private JTextField x,y;
	private JComboBox<String> strockedColor,filledColor;
	private JButton ok;
	private JRadioButton filledTrue,filledFalse,strokedTrue,strokedFalse;

	public Dialog() {
		super();
		super.setSize(520, 400);
		super.setLocationRelativeTo(null);
	}
	public JPanel panelPosition(){
		JPanel panelPosition = new JPanel();
		panelPosition.setBorder(BorderFactory.createTitledBorder(POSITION));
		panelPosition.setPreferredSize(DEFAULT_DIMENSION_PANEL);
		JLabel xLabel = new JLabel(X_LABEL);
		panelPosition.add(xLabel);
		this.x = new JTextField(Integer.toString(DEFAULT_VALUE));
		this.x.setPreferredSize(DEFAULT_DIMENSION_TEXT_FIELD);
		panelPosition.add(x);
		JLabel yLabel = new JLabel(Y_LABEL);
		panelPosition.add(yLabel);
		this.y = new JTextField(Integer.toString(DEFAULT_VALUE));
		this.y.setPreferredSize(DEFAULT_DIMENSION_TEXT_FIELD);
		panelPosition.add(y);
		return panelPosition;
	}
	
	public JPanel panelColor() {
		JPanel panelColor = new JPanel();
		panelColor.setBorder(BorderFactory.createTitledBorder(COLOR));
		this.strockedColor = new JComboBox<String>();
		this.filledColor = new JComboBox<String>();
		JLabel strockedColorLabel = new JLabel(STROKED_COLOR_LABEL);
		JLabel filledColorLabel = new JLabel(FILLED_COLOR_LABEL);
		strockedColor.addItem(RED);
		strockedColor.addItem(BLUE);
		strockedColor.addItem(YELLOW);
		strockedColor.addItem(BLACK);
		strockedColor.addItem(MAGENTA);
		filledColor.addItem(RED);
		filledColor.addItem(BLUE);
		filledColor.addItem(YELLOW);
		filledColor.addItem(BLACK);
		filledColor.addItem(MAGENTA);
		panelColor.add(strockedColorLabel);
		panelColor.add(strockedColor);
		panelColor.add(filledColorLabel);
		panelColor.add(filledColor);
		return panelColor;
	}
	
	public JPanel panelFilled() {
		JPanel panelFilled = new JPanel();
		panelFilled.setBorder(BorderFactory.createTitledBorder(STROKED));
		panelFilled.setPreferredSize(DEFAULT_DIMENSION_PANEL);
		JLabel filledLabel = new JLabel(FILLED_LABEL);
		panelFilled.add(filledLabel);
		this.filledTrue = new JRadioButton(TRUE);
		this.filledTrue.setSelected(true);
		this.filledFalse = new JRadioButton(FALSE);
		ButtonGroup filledButtons = new ButtonGroup();
		filledButtons.add(this.filledTrue);
		filledButtons.add(this.filledFalse);
		panelFilled.add(this.filledTrue);
		panelFilled.add(this.filledFalse);
		return panelFilled;
	}
	
	public JPanel panelStrocked() {
		JPanel panelStrocked = new JPanel();
		panelStrocked.setBorder(BorderFactory.createTitledBorder(FILLED));
		panelStrocked.setPreferredSize(DEFAULT_DIMENSION_PANEL);
		JLabel strockedLabel = new JLabel(STROKED_LABEL);
		panelStrocked.add(strockedLabel);
		this.strokedTrue = new JRadioButton(TRUE);
		this.strokedTrue.setSelected(true);
		this.strokedFalse = new JRadioButton(FALSE);
		ButtonGroup strockedButtons = new ButtonGroup();
		strockedButtons.add(this.strokedTrue);
		strockedButtons.add(this.strokedFalse);
		panelStrocked.add(this.strokedTrue);
		panelStrocked.add(this.strokedFalse);
		return panelStrocked;
	}
	
	public JPanel panelValidation() {
		JPanel panelValidation = new JPanel();
		this.ok = new JButton(OK);
		panelValidation.add(ok);
		ok.addActionListener(this);
		return panelValidation;
	}
	
	public JPanel panelSettings(JPanel panelPosition,JPanel panelFilled, JPanel panelStrocked, JPanel panelColor) {
		JPanel panelSettings = new JPanel();
		panelSettings.add(panelPosition);
		panelSettings.add(panelFilled);
		panelSettings.add(panelStrocked);
		panelSettings.add(panelColor);
		return panelSettings;
	}
	
	
	
	public Color getColor(String col) {
		Color color = Color.BLACK;
	    switch (col.toLowerCase()) {
	    case "blue":
	        color = Color.BLUE;
	        break;
	    case "yellow":
	        color = Color.YELLOW;
	        break;
	    case "magenta":
	        color = Color.MAGENTA;
	        break;
	    case "red":
	        color = Color.RED;
	        break;
	     }
	    return color;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
	}

	public boolean getfTrue() {
		return this.filledTrue.isSelected();
	}

	public boolean getsTrue() {
		return this.strokedTrue.isSelected();
	}

	public int getX() {
		if (this.x != null) {
			return Integer.parseInt(this.x.getText());
		}
		return DEFAULT_VALUE;
	
	}

	public int getY() {
		if (this.y != null) {
			return Integer.parseInt(this.y.getText());
		}
		return DEFAULT_VALUE;
	}
	
	public JButton getOk() {
		return this.ok;
	}

	public Color getStrockedColor() {
		return this.getColor((String) strockedColor.getSelectedItem());
	}


	public Color getFilledColor() {
		return this.getColor((String) filledColor.getSelectedItem());
	}


	
	
}
