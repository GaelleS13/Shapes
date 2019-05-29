package graphics.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import graphics.shapes.Shape;
import graphics.shapes.ui.Editor;
import graphics.shapes.ui.ShapesController;


public class MenuBar implements ActionListener{
	
	private JMenuBar menuBar;
	private JMenuItem rectangle;
	private JMenuItem circle;
	private JMenuItem text;
	private JMenuItem delete;

	
	
	public MenuBar(){
		
		this.menuBar = new JMenuBar();
		
		JMenu fichier = new JMenu("Fichier");
		JMenu creer = new JMenu("Créer nouveau ...");
		this.delete = new JMenuItem("Delete");
		this.delete.addActionListener(this);

		this.rectangle = new JMenuItem("Rectangle");
		this.circle = new JMenuItem("Circle");
		this.text = new JMenuItem("Text");
		this.rectangle.addActionListener(this);
		this.circle.addActionListener(this);
		this.text.addActionListener(this);

	    fichier.add(creer);
	    fichier.add(delete);

	    creer.add(rectangle);
	    creer.add(circle);
	    creer.add(text);
	    
	    menuBar.add(fichier);
	    

	}

	@Override
	 public void actionPerformed(ActionEvent evt) {
		 Object source = evt.getSource();

		 if (source.equals(rectangle)) {
			 DialogRectangle dr = new DialogRectangle();
			 dr.setVisible(true);
		 }
		 else if (source.equals(circle)) {
			 DialogCircle dc = new DialogCircle();
			 dc.setVisible(true);
		 }
		 else if (source.equals(text)) {
			 DialogText dt= new DialogText();
			 dt.setVisible(true);
		 }
		 else if (source.equals(delete)) {
				for (Iterator<Shape> it= ShapesController.selected.iterator();it.hasNext();) {
					Shape shape = it.next();
					Editor.model.remove(shape);
					
				}
		 }
		 Editor.sview.repaint();
	}
	
	public JMenuBar getMenu() {
		return this.menuBar;
	}
}
