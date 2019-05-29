
package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

public class SArrow extends Shape {
	private Polygon poly;
	private Point loc;
	private int width;
	private int height;

	public SArrow(Point loc, int width, int height) {
		this.loc = loc;
		int xloc[] = { loc.x, width / 2 + loc.x, width / 2 + loc.x, width + loc.x, width / 2 + loc.x, width / 2 + loc.x,+loc.x };
		int yloc[] = { height / 4 + loc.y, height / 4 + loc.y, loc.y, height / 2 + loc.y, height + loc.y,3 * height / 4 + loc.y, 3 * height / 4 + loc.y };
		this.poly = new Polygon(xloc, yloc, 7);
		this.height = height;
		this.width = width;
	}

	public Polygon getPoly() {
		return this.poly;

	}

	@Override
	public void setLoc(Point loc) {
		this.loc = loc;
	}
	
	@Override
	public Point getLoc() {
		return this.loc;
	}

	
	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	@Override
	public void translate(int x, int y) {
		this.poly.translate(x, y);
		this.loc.setLocation(this.loc.x + x, this.loc.y + y);
	}

	@Override
	public Rectangle getBounds() {
		return this.poly.getBounds();
	}
	
	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visitSArrow(this);
	}

	@Override
	public void setSize(int dw, int dh) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void grow(boolean width, boolean height, int growingSpeed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shrink(boolean width, boolean height, int growingSpeed) {
		// TODO Auto-generated method stub
		
	}
}
