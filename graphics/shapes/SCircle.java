package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class SCircle extends Shape {

	private int radius;
	private Point loc;
	
	public SCircle(Point loc, int radius) {
		this.loc = loc;
		this.radius = radius;
	}
	
	public int getRadius() {
		return this.radius;
	}
	
	public void setRadius(double radius) {
		this.radius=(int) radius;
	}
	
	@Override
	public Point getLoc() {
		return this.loc;
	}

	@Override
	public void setLoc(Point point) {
		this.loc = point;
	}

	@Override
	public void translate(int dx, int dy) {
		this.loc.translate(dx,dy);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.loc.x,this.loc.y,2*this.radius,2*this.radius);
	}

	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitCircle(this);
	}

	@Override
	public void setSize(int dw, int dh) {
		double d =(Math.sqrt(Math.pow(dw,2)+Math.pow(dh,2))/2);
		
		if(dw<0 || dh<0) {
			setRadius(this.radius-d);
		}
		else {
			setRadius(this.radius+d);
		}
	}
	
	@Override
	public void grow(boolean width, boolean height, int growingSpeed) {
		this.radius+=growingSpeed;
	}
	
	@Override
	public void shrink(boolean width, boolean height, int growingSpeed) {
		if(this.radius>0) {
			this.radius-=growingSpeed;
		}
	}


}
