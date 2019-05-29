package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;


public class SCollection extends Shape {
	
	private ArrayList<Shape> collection;
	private Rectangle bounds;
	
	public SCollection() {
		this.collection = new ArrayList<Shape>();
		this.bounds = new Rectangle();
	}

	public Iterator<Shape> iterator() {
		return this.collection.iterator();
	}
	
	public void add(Shape shape) {
		collection.add(shape);
	}
	
	public void remove(Shape shape) {
		collection.remove(shape);
	}

	@Override
	public Point getLoc() {
		return this.getBounds().getLocation();
	}

	@Override
	public void setLoc(Point point) {
		for (Iterator<Shape> it= this.collection.iterator();it.hasNext();)
			it.next().setLoc(point);
	}

	@Override
	public void translate(int dx, int dy) {
		for (Iterator<Shape> it= this.collection.iterator();it.hasNext();)
			it.next().translate(dx, dy);
	}

	@Override
	public Rectangle getBounds() {
		bounds = collection.get(0).getBounds();
		for (Iterator<Shape> it= this.collection.iterator();it.hasNext();)
			bounds = bounds.union(it.next().getBounds());
		return bounds;
	}

	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitCollection(this);
	}
	
	public ArrayList<Shape> getCollection(){
		return collection;
	}
	
	@Override
	public void setSize(int dw, int dh) {
		for(Iterator<Shape> it=collection.iterator();it.hasNext();){
			it.next().setSize(dw, dh);
		}
	}
	
	@Override
	public void grow(boolean width, boolean height, int growingSpeed) {
		for(Iterator<Shape> iterator = this.iterator(); iterator.hasNext();) {
			Shape current=iterator.next();
			current.grow(width, height, growingSpeed);
		}
	}
	
	@Override
	public void shrink(boolean width, boolean height, int growingSpeed) {
		for(Iterator<Shape> iterator = this.iterator(); iterator.hasNext();) {
			Shape current=iterator.next();
			current.shrink(width, height, growingSpeed);
		}
	}

}
