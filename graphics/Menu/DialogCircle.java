package graphics.Menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import graphics.shapes.SCircle;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.Editor;

public class DialogCircle extends Dialog {

	private static final long serialVersionUID = 1L;
	
	private static final Dimension DEFAULT_DIMENSION_PANEL = new Dimension(250,60);
	private static final Dimension DEFAULT_DIMENSION_TEXT_FIELD = new Dimension(60,25);
	private static final int DEFAULT_VALUE=100;
	private static final String DEFAULT_TITLE = "Créer un nouveau cercle";
	private static final String RADIUS = "Radius";
	private static final String RADIUS_LABEL = "radius : ";

	private JTextField radius;

	public DialogCircle() {
		
		super.setTitle(DEFAULT_TITLE);
		
		JPanel panelPosition = super.panelPosition();	
		JPanel panelColor = super.panelColor();
		JPanel panelFilled = super.panelFilled();
		JPanel panelStrocked = super.panelStrocked();
		JPanel panelValidation = super.panelValidation();
		JPanel panelRadius = this.panelRadius();
		JPanel panelSettings = super.panelSettings(panelPosition, panelFilled, panelStrocked, panelColor);
		panelSettings.add(panelRadius);
	
	    this.getContentPane().add(panelSettings, BorderLayout.CENTER);
	    this.getContentPane().add(panelValidation, BorderLayout.SOUTH);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(super.getOk())) {
			SCircle c = new SCircle(new Point(getX(),getY()),this.getRadius());
			c.addAttributes(new ColorAttributes(super.getfTrue(),super.getsTrue(),super.getFilledColor(),super.getStrockedColor()));
			c.addAttributes(new SelectionAttributes());
			Editor.model.add(c);
			Editor.sview.repaint();
			super.setVisible(false);
		}
	}
	
	public JPanel panelRadius() {
		JPanel panelRadius = new JPanel();
		panelRadius.setBorder(BorderFactory.createTitledBorder(RADIUS));
		panelRadius.setPreferredSize(DEFAULT_DIMENSION_PANEL);
		JLabel radiusLabel = new JLabel(RADIUS_LABEL);
		panelRadius.add(radiusLabel);
		this.radius = new JTextField(Integer.toString(DEFAULT_VALUE));
		this.radius.setPreferredSize(DEFAULT_DIMENSION_TEXT_FIELD);
		panelRadius.add(this.radius);
		
		return panelRadius;
	}
	
	public int getRadius() {
		return Integer.parseInt(this.radius.getText());
	}

}