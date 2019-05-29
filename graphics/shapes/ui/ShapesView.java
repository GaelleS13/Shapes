package graphics.shapes.ui;

import java.awt.Graphics;
import graphics.shapes.SCollection;
import graphics.ui.Controller;
import graphics.ui.View;


public class ShapesView extends View{

	private static final long serialVersionUID = 1L;
	private ShapeDraftman sd;

	public ShapesView(Object model) {
		super(model);
		

	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		SCollection collection = (SCollection) this.getModel();
		this.sd = new ShapeDraftman(g);
		collection.accept(sd);
	}
	


	@Override
	public Controller defaultController(Object model) {
		return new ShapesController((SCollection) model);
	}

	@Override
	public boolean isFocusable() {
		return true;
	}
	
	
}
