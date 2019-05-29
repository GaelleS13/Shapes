package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Map;
import java.util.TreeMap;
import graphics.shapes.attributes.Attributes;

public abstract class Shape {
	
	private Map<String , Attributes> attributes = new TreeMap<String , Attributes>();
	
	public void addAttributes(Attributes attribut) {
		attributes.put(attribut.getId(),attribut);
	}
	
	public Attributes getAttributes(String id) {
		return attributes.get(id);
	}
	
	public abstract Point getLoc();
	public abstract void setLoc(Point point);
	public abstract void translate(int dx,int dy);
	public abstract Rectangle getBounds();
	public abstract void accept(ShapeVisitor sv);
	public abstract void setSize(int dw, int dh);
	public abstract void grow(boolean width, boolean height, int growingSpeed);
	public abstract void shrink(boolean width, boolean height, int growingSpeed);
}
