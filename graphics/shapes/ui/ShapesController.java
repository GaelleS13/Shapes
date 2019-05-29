package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;

import javax.swing.JOptionPane;

import graphics.shapes.SArrow;
import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SDiamond;
import graphics.shapes.SEllipse;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.STriangle;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.ImageAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

public class ShapesController extends Controller {
	
	public static HashSet<Shape> selected;
	private ArrayList<Shape> collection;
	private boolean pressedOnShape;
	private boolean shiftDown;
	private int xPressed;
	private int yPressed;
	private HashSet<Shape> copiedShapes;
	private int growingSpeed=1;
	public static boolean r1;
	public static boolean r2;
	public static boolean r3;
	public static boolean r4;
	public static Hashtable<String, Color> color;
	private static final Object[] colors = { "Black", "Blue", "Cyan", "Dark Gray", "Gray", "Light Gray", "Green",
			"Magenta", "Orange", "Pink", "Red", "White", "Yellow", "None" };
	private static final Object[] directions = { "To the left", "To the right", "To the top", "To the bottom" };
	
	public ShapesController(Object newModel) {
		super(newModel);
		ShapesController.selected = new HashSet<Shape>();
		this.collection = new ArrayList<Shape>();
		this.copiedShapes = new HashSet<Shape>();
		this.shiftDown=false;
		this.pressedOnShape=false;
		ShapesController.r1=false;
		ShapesController.r2=false;
		ShapesController.r3=false;
		ShapesController.r4=false;
		ShapesController.color = new Hashtable<String, Color>();
		color.put("Black", Color.black);
		color.put("Blue", Color.blue);
		color.put("Cyan", Color.cyan);
		color.put("Dark Gray", Color.darkGray);
		color.put("Gray", Color.gray);
		color.put("Green", Color.green);
		color.put("Light Gray", Color.lightGray);
		color.put("Magenta", Color.magenta);
		color.put("Orange", Color.orange);
		color.put("Pink", Color.pink);
		color.put("Red", Color.red);
		color.put("White", Color.white);
		color.put("Yellow", Color.yellow);
	}
	
	public Shape getTarget(MouseEvent e)
	{
		SCollection collection = (SCollection) getModel();
		for (Iterator<Shape> it= collection.iterator();it.hasNext();) {
			Shape shape = it.next();
			if (shape.getBounds().contains(e.getX(), e.getY())){
				return shape;
			}
		}
		return null;
	}
	
	public void select(Shape shape) {
		SelectionAttributes sa =(SelectionAttributes) shape.getAttributes(SelectionAttributes.ID);
		sa.select();
		selected.add(shape);
	}
	
	public void unselect(Shape shape) {
		SelectionAttributes sa =(SelectionAttributes) shape.getAttributes(SelectionAttributes.ID);
		sa.unselect();
		selected.remove(shape);
	}
	
	public void selectAll() {
		for (Iterator<Shape> it= Editor.model.iterator();it.hasNext();) {
			Shape shape = it.next();
			SelectionAttributes sa =(SelectionAttributes) shape.getAttributes(SelectionAttributes.ID);
			sa.select();
			selected.add(shape);
		}
	}
	
	public void unselectAll() {
		for (Iterator<Shape> it= Editor.model.iterator();it.hasNext();) {
			Shape shape = it.next();
			SelectionAttributes sa =(SelectionAttributes) shape.getAttributes(SelectionAttributes.ID);
			sa.unselect();
		}
		selected.clear();
	}
	
	public void translateSelected(int dx,int dy) {
		for (Shape shape : selected) {
			shape.translate(dx, dy);
		}
	}
	
	
	@Override
	public void mousePressed(MouseEvent e)
	{	
		xPressed=e.getX();
		yPressed=e.getY();
		Shape target = getTarget(e);
		
		if (selected.isEmpty() && target!=null) {
			this.select(target);
		}
		
		if (!selected.isEmpty()) {
			for (Shape shape : selected) {
				if (shape.getBounds().contains(e.getX(),e.getY())) {
					this.pressedOnShape=true;
				}
			}
		}
		
		if (!selected.isEmpty() && !selected.contains(target) && !this.shiftDown && target!=null) {
			this.unselectAll();
			this.select(target);
			this.pressedOnShape=true;
			
		}
		
		if(!selected.isEmpty()) {
			ArrayList<Shape> selec = new ArrayList<Shape>(selected);
			Shape s =(Shape) selec.get(0);
			if(((SelectionAttributes) s.getAttributes(SelectionAttributes.ID)).isSelected()){
				Rectangle r1 =new Rectangle(s.getBounds().x-10, s.getBounds().y-10,10,10);
				Rectangle r2 =new Rectangle(s.getBounds().x+s.getBounds().width, s.getBounds().y+s.getBounds().height,10,10);
				Rectangle r3 =new Rectangle(s.getBounds().x+s.getBounds().width, s.getBounds().y-10, 10,10);
				Rectangle r4 =new Rectangle(s.getBounds().x-10, s.getBounds().y+s.getBounds().height, 10,10);
				if(r1.contains(e.getPoint())) {
					ShapesController.r1=true;

				}
				if(r2.contains(e.getPoint())) {
					ShapesController.r2=true;
				}
				if(r3.contains(e.getPoint())) {
					ShapesController.r3=true;

				}
				if(r4.contains(e.getPoint())) {
					ShapesController.r4=true;
				}
			}
			}
		
		super.getView().repaint();
	}

	public void mouseReleased(MouseEvent e)
	{	
		Shape shape = this.getTarget(e);
		if (shape ==null) {
			this.unselectAll();
		}
		this.pressedOnShape=false;
		ShapesController.r1=false;
		ShapesController.r2=false;
		ShapesController.r3=false;
		ShapesController.r4=false;
		super.getView().repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		Shape shape = getTarget(e);
		
		if (!shiftDown || shape==null) {
			this.unselectAll();
		}
		if (shape!=null) {
			this.select(shape);
		}
		
		super.getView().repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent evt)
	{
		Shape shape = this.getTarget(evt);
		if (shape != null && selected.isEmpty()) {
			this.select(shape);
			this.pressedOnShape=true;
		}
		
		if (!selected.isEmpty() && this.pressedOnShape==true) {
			this.translateSelected(evt.getX()-xPressed,evt.getY()-yPressed);
			xPressed=evt.getX();
			yPressed=evt.getY();
		}
		if (this.pressedOnShape==false) {
			//this.unselectAll();
		}
		
		
		ArrayList<Shape> selec = new ArrayList<Shape>(selected);
		if(r2) {
			if(evt.getPoint().x <= selec.get(0).getBounds().x) {
				ShapesController.r2=false;
				ShapesController.r4=true;
			}
			else if(evt.getPoint().y <= selec.get(0).getBounds().y) {
				ShapesController.r2=false;
				ShapesController.r3=true;
			}
			else {
				int dw = evt.getPoint().x-this.xPressed;
				int dh = evt.getPoint().y-this.yPressed;
				selec.get(0).setSize(dw, dh);
				this.xPressed =evt.getPoint().x;
				this.yPressed =evt.getPoint().y;
				System.out.println("r2");
			}
			
		}
		if(r1) {
			if(evt.getPoint().x >= selec.get(0).getBounds().x+selec.get(0).getBounds().width) {
				ShapesController.r1=false;
				ShapesController.r3=true;
				System.out.println("dans t");
			}
			else if(evt.getPoint().y >= selec.get(0).getBounds().y+selec.get(0).getBounds().height) {
				ShapesController.r1=false;
				ShapesController.r2=true;
				System.out.println("dans h");
			}
			else {
				int dw = this.xPressed-evt.getPoint().x;
				int dh = this.yPressed-evt.getPoint().y;
				Point pt = new Point(evt.getPoint().x+5,evt.getPoint().y+5);
				selec.get(0).setLoc(pt);
				selec.get(0).setSize(dw, dh);
				this.xPressed =evt.getPoint().x;
				this.yPressed =evt.getPoint().y;
				System.out.println("r1");

			}
		}
		if(r4) {
			if(evt.getPoint().x >= selec.get(0).getBounds().x+selec.get(0).getBounds().width) {
				ShapesController.r4=false;
				ShapesController.r2=true;
			}
			else if(evt.getPoint().y <= selec.get(0).getBounds().y) {
				ShapesController.r4=false;
				ShapesController.r1=true;
			}
			else {
				int dw = this.xPressed-evt.getPoint().x;
				int dh = evt.getPoint().y-this.yPressed;
				Point pt = new Point(evt.getPoint().x+5,selec.get(0).getLoc().y);
				selec.get(0).setLoc(pt);
				selec.get(0).setSize(dw, dh);
				this.xPressed =evt.getPoint().x;
				this.yPressed =evt.getPoint().y;
				System.out.println("r4");
			}
		}
		if(r3) {
			if(evt.getPoint().x <= selec.get(0).getBounds().x) {
				ShapesController.r3=false;
				ShapesController.r1=true;
			}
			else if(evt.getPoint().y >= selec.get(0).getBounds().y+selec.get(0).getBounds().height) {
				ShapesController.r3=false;
				ShapesController.r2=true;
			}
			else {
				int dw = evt.getPoint().x-this.xPressed;
				int dh = this.yPressed-evt.getPoint().y;
				Point pt = new Point(selec.get(0).getLoc().x,evt.getPoint().y+5);
				selec.get(0).setLoc(pt);
				selec.get(0).setSize(dw, dh);
				this.xPressed =evt.getPoint().x;
				this.yPressed =evt.getPoint().y;
				System.out.println("r3");
			}
		}
		
		
		super.getView().repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent evt)
	{
		int key = evt.getKeyCode();
		System.out.println(key);
		switch (key) 
		{
		case KeyEvent.VK_DELETE:
			this.deleteSelection();
			break;
		case KeyEvent.VK_LEFT:
			this.translateLeft();
			break;
		case KeyEvent.VK_UP:
			this.translateUp();
			break;
		case KeyEvent.VK_RIGHT:
			this.translateRight();
			break;
		case KeyEvent.VK_DOWN:
			this.translateDown();	
			break;
		case KeyEvent.VK_SHIFT:
			this.shiftDown();
			break;
		case KeyEvent.VK_SPACE:
			this.createSCollection();
			break;
		case KeyEvent.VK_ENTER:
			this.separateSCollection();
			break;
		case KeyEvent.VK_SUBTRACT:
			this.shrink();
			break;
		case KeyEvent.VK_ADD:
			this.grow();
			break;
		}
		
		if(evt.isControlDown()) {
			switch (key) 
			{
			case KeyEvent.VK_C:
				this.copy();
				break;
			case KeyEvent.VK_V:
		    	this.paste();
				break;
			case KeyEvent.VK_X:
		    	this.cut();
				break;
			case KeyEvent.VK_SUBTRACT:
				this.shrinkWidth();
				break;
			case KeyEvent.VK_ADD:
				this.growWidth();
				break;
			case KeyEvent.VK_A:
				this.selectAll();
				break;
			case KeyEvent.VK_R:
				this.rename();
				break;
			case KeyEvent.VK_T:
				this.changeStrok();
				break;
			case KeyEvent.VK_F:
				this.changeFill();
				break;
			case KeyEvent.VK_G:
				this.grad();
				break;
			case KeyEvent.VK_H:
				this.changeText();
				break;
			}
		}
		
		if(evt.isShiftDown()) {
			switch (key) 
			{
			case KeyEvent.VK_SUBTRACT:
				this.shrinkHeight();
				break;
			case KeyEvent.VK_ADD:
				this.growHeight();
				break;
			}
		}
		
		super.getView().repaint();
	}

	public void changeText() {
		for (Shape shape : selected) {
			if (shape instanceof SText) {
				Object txt = JOptionPane.showInputDialog(null, "Choose a new color", "New color",
						JOptionPane.INFORMATION_MESSAGE, null, colors, colors[0]);
				if ((String) txt != null) {
					ColorAttributes ca = (ColorAttributes) shape.getAttributes(ColorAttributes.ID);
					if ((String) txt != "None") {
						shape.addAttributes(new ColorAttributes(ca.isFilled(), true, ca.isGrad(), ca.getFilledColor(), color.get((String) txt), ca.getStart(), ca.getEnd(),ca.getDirection()));
					}
				}
			}
		}
		
	}

	private void grad() {
		for (Shape shape : selected) {
			if (!(shape instanceof SCollection)) {
				Object txt = JOptionPane.showInputDialog(null, "Choose a start color for the gradient", "New color",
						JOptionPane.INFORMATION_MESSAGE, null, colors, colors[0]);
				Object txt2 = JOptionPane.showInputDialog(null, "Choose an end color for the gradient", "New color",
						JOptionPane.INFORMATION_MESSAGE, null, colors, colors[0]);
				Object txt3 = JOptionPane.showInputDialog(null, "Choose a direction for the gradient", "Direction",
						JOptionPane.INFORMATION_MESSAGE, null, directions, directions[0]);
				if(((String)txt!=null)&&((String)txt2!=null)&&((String)txt3!=null)&&((String)txt!="None")&&((String)txt2!="None")) {
					ColorAttributes ca = (ColorAttributes) shape.getAttributes(ColorAttributes.ID);
					shape.addAttributes(new ColorAttributes(false, ca.isStroked(), true, null,ca.getStrokedColor(), color.get((String) txt), color.get((String) txt2),(String) txt3));
				}
			}
		}
		super.getView().repaint();

	}

	private void changeFill() {
		for (Shape shape : selected) {
			if (!(shape instanceof SCollection)) {
				Object txt = JOptionPane.showInputDialog(null, "Choose a new color", "New color",
						JOptionPane.INFORMATION_MESSAGE, null, colors, colors[0]);
				if (((String) txt != null) && (!(shape instanceof SCollection))) {
					ColorAttributes ca = (ColorAttributes) shape.getAttributes(ColorAttributes.ID);
					if ((String) txt == "None") {
						shape.addAttributes(new ColorAttributes(false, ca.isStroked(), false, color.get((String) txt),
								ca.getStrokedColor(), null, null,null));
					} else {
						shape.addAttributes(new ColorAttributes(true, ca.isStroked(), false, color.get((String) txt),
								ca.getStrokedColor(), null, null,null));
					}
				}
			}
		}
		super.getView().repaint();

	}

	private void changeStrok() {
		for (Shape shape : selected) {
			if (!(shape instanceof SCollection)) {
				Object txt = JOptionPane.showInputDialog(null, "Choose a new color", "New color",
						JOptionPane.INFORMATION_MESSAGE, null, colors, colors[0]);
				if ((String) txt != null) {
					ColorAttributes ca = (ColorAttributes) shape.getAttributes(ColorAttributes.ID);
					if ((String) txt == "None") {
						shape.addAttributes(new ColorAttributes(ca.isFilled(), false, ca.isGrad(), ca.getFilledColor(),
								color.get((String) txt), ca.getStart(), ca.getEnd(),ca.getDirection()));
					} else {
						shape.addAttributes(new ColorAttributes(ca.isFilled(), true, ca.isGrad(), ca.getFilledColor(),
								color.get((String) txt), ca.getStart(), ca.getEnd(),ca.getDirection()));
					}
				}
			}
		}
		super.getView().repaint();

	}

	@Override
	public void keyReleased(KeyEvent evt)
	{
		int key = evt.getKeyCode();
		if (key==KeyEvent.VK_SHIFT) {
			this.unShiftDown();
		}
		
		super.getView().repaint();
	}
	
	public void separateSCollection(){
			for (Shape shape : selected) {
				if (shape instanceof SCollection) {
					for (Shape s :((SCollection) shape).getCollection()) {
						SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
						sa.unselect();
						collection.add(s);		
					}
				}
				else {
					collection.add(shape);
				}
				Editor.model.remove(shape);
			}
			this.unselectAll();
			for (Shape shape : this.collection) {
				this.select(shape);
				Editor.model.add(shape);
			}
			collection.clear();
	}
	
	public void createSCollection() {
		if (!selected.isEmpty()) {
			SCollection sc = new SCollection();
			sc.addAttributes(new SelectionAttributes());

			for (Shape shape : selected) {
				if (shape instanceof SCollection) {
					for (Shape s :((SCollection) shape).getCollection()) {
						collection.add(s);		
					}
				}
				else {
					this.collection.add(shape);
				}
				SelectionAttributes sa = (SelectionAttributes) shape.getAttributes(SelectionAttributes.ID);
				sa.unselect();
				Editor.model.remove(shape);
			}
			selected.clear();
			for (Shape shape : this.collection) {
				sc.add(shape);
			}
			this.select(sc);
			Editor.model.add(sc);
			collection.clear();
		}
	}

	public Shape duplicate(Shape s) {
		Shape shape = null;
		if (s instanceof SRectangle) {
			SRectangle rec = (SRectangle) s;
			shape = new SRectangle(new Point(rec.getLoc().x + 5, rec.getLoc().y + 5), rec.getRect().width,rec.getRect().height);
			ColorAttributes ca = (ColorAttributes) rec.getAttributes(ColorAttributes.ID);
			shape.addAttributes(new ColorAttributes(ca.isFilled(), ca.isStroked(), ca.isGrad(), ca.getFilledColor(),
					ca.getStrokedColor(), ca.getStart(), ca.getEnd(),ca.getDirection()));
			shape.addAttributes(new ImageAttributes());
		} 
		else if (s instanceof SCircle) {
			SCircle cir = (SCircle) s;
			shape = new SCircle(new Point(cir.getLoc().x + 5, cir.getLoc().y + 5), cir.getRadius());
			ColorAttributes ca = (ColorAttributes) cir.getAttributes(ColorAttributes.ID);
			shape.addAttributes(new ColorAttributes(ca.isFilled(), ca.isStroked(), ca.isGrad(), ca.getFilledColor(),
					ca.getStrokedColor(), ca.getStart(), ca.getEnd(),ca.getDirection()));
		} 
		else if (s instanceof SText) {
			SText txt = (SText) s;
			shape = new SText(new Point(txt.getLoc().x + 5, txt.getLoc().y + 5), txt.getText());
			shape.addAttributes((FontAttributes) s.getAttributes(FontAttributes.ID));
			ColorAttributes ca = (ColorAttributes) txt.getAttributes(ColorAttributes.ID);
			shape.addAttributes(new ColorAttributes(ca.isFilled(), ca.isStroked(), ca.isGrad(), ca.getFilledColor(),
					ca.getStrokedColor(), ca.getStart(), ca.getEnd(),ca.getDirection()));

		} 
		else if (s instanceof SArrow) {
			SArrow arrow = (SArrow) s;
			shape = new SArrow(new Point(arrow.getLoc().x + 5, arrow.getLoc().y + 5), arrow.getWidth(),arrow.getHeight());
			ColorAttributes ca = (ColorAttributes) arrow.getAttributes(ColorAttributes.ID);
			shape.addAttributes(new ColorAttributes(ca.isFilled(), ca.isStroked(), ca.isGrad(), ca.getFilledColor(),
					ca.getStrokedColor(), ca.getStart(), ca.getEnd(),ca.getDirection()));
		} 
		else if (s instanceof SDiamond){
			SDiamond dia = (SDiamond) s;
			shape = new SDiamond(new Point(dia.getLoc().x + 5, dia.getLoc().y + 5+dia.getHeight()/2), new Point(dia.getLoc().x + 5+dia.getWidth()/2, dia.getLoc().y + 5));
			ColorAttributes ca = (ColorAttributes) dia.getAttributes(ColorAttributes.ID);
			shape.addAttributes(new ColorAttributes(ca.isFilled(), ca.isStroked(), ca.isGrad(), ca.getFilledColor(),
					ca.getStrokedColor(), ca.getStart(), ca.getEnd(),ca.getDirection()));
		} 
		else if (s instanceof STriangle){
			STriangle tri = (STriangle) s;
			shape = new STriangle(new Point(tri.getP1().x+5,tri.getP1().y+5),new Point(tri.getP2().x+5,tri.getP2().y+5),new Point(tri.getP3().x+5,tri.getP3().y+5));
			ColorAttributes ca = (ColorAttributes) tri.getAttributes(ColorAttributes.ID);
			shape.addAttributes(new ColorAttributes(ca.isFilled(), ca.isStroked(), ca.isGrad(), ca.getFilledColor(),
		ca.getStrokedColor(), ca.getStart(), ca.getEnd(),ca.getDirection()));
		} 
		else if (s instanceof SEllipse){
			SEllipse el = (SEllipse) s;
			shape = new SEllipse(new Point(el.getLoc().x + 5, el.getLoc().y + 5),el.getWidth(),el.getHeight());
			ColorAttributes ca = (ColorAttributes) el.getAttributes(ColorAttributes.ID);
			shape.addAttributes(new ColorAttributes(ca.isFilled(), ca.isStroked(), ca.isGrad(), ca.getFilledColor(),
					ca.getStrokedColor(), ca.getStart(), ca.getEnd(),ca.getDirection()));
		} 
		else if (s instanceof SCollection) {
			SCollection col = (SCollection) s;
			shape = new SCollection();
			for (Shape shape1 : col.getCollection()) {
				((SCollection) shape).add(duplicate(shape1));
			}
		}
		shape.addAttributes(new SelectionAttributes());
		return shape;
	}
	
	public void copy() {
		copiedShapes.clear();
		for (Shape shape : selected) {
			if (shape != null) {
				copiedShapes.add(shape);
			}
		}
	}
	
	public void cut() {
		copiedShapes.clear();
		for (Shape shape : selected) {
			if (shape != null) {
				copiedShapes.add(shape);
				Editor.model.remove(shape);
			}

		}
		super.getView().repaint();
	}

	public void paste() {
		HashSet<Shape> copy = new HashSet<Shape>();
		for (Shape shape : copiedShapes) {
			if (shape != null) {
				SCollection col = (SCollection) getModel();
				Shape s = duplicate(shape);
				col.add(s);
				copy.add(s);
			}
		}
		copiedShapes.clear();
		copiedShapes = copy;

	}
	
	public void rename() {
		for (Shape shape : selected) {
			if (shape instanceof SText) {
				String txt = JOptionPane.showInputDialog("Please enter a new text : ");
				if (txt != null) {
					((SText) shape).setText(txt);
				}
			}
		}
		super.getView().repaint();
	}
	
	public void shrink() {
		for(Shape shape : selected) {
			shape.shrink(true,true,growingSpeed);
		}
	}
	
	public void shrinkHeight() {
		for(Shape shape : selected) {
			shape.shrink(false,true,growingSpeed);
		}
	}
	
	public void shrinkWidth() {
		for(Shape shape : selected) {
			shape.shrink(true,false,growingSpeed);
		}
	}
	
	public void grow() {
		for(Shape shape : selected) {
			shape.grow(true,true,growingSpeed);
		}
	}
	
	public void growHeight() {
		for(Shape shape : selected) {
			shape.grow(false,true,growingSpeed);
		}
	}
	
	public void growWidth() {
		for(Shape shape : selected) {
			shape.grow(true,false,growingSpeed);
		}
	}
	
	public void deleteSelection() {
		for (Shape shape : ShapesController.selected) {
			Editor.model.remove(shape);
		}
		selected.clear();
	}
	
	public void translateLeft() {
		for (Shape shape : ShapesController.selected) {
			shape.translate(-3, 0);
		}	
	}
	
	public void translateRight() {
		for (Shape shape : ShapesController.selected) {
			shape.translate(3, 0);
		}	
	}
	
	public void translateUp() {
		for (Shape shape : ShapesController.selected) {
			shape.translate(0, -3);
		}	
	}
	
	public void translateDown() {
		for (Shape shape : ShapesController.selected) {
			shape.translate(0, 3);
		}	
	}
	
	public void shiftDown() {
		this.shiftDown=true;
	}
	
	public void unShiftDown() {
		this.shiftDown=false;
	}
}