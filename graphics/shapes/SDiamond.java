package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

public class SDiamond extends Shape {
	private Polygon diam=new Polygon();
	
	private Point one;
	
	private Point two;
	
	public SDiamond(Point one, Point two) {
		this.one=one;
		this.two=two;
		int X[]= {one.x,two.x,one.x+2*(two.x-one.x),two.x};
		int Y[]= {one.y,two.y,one.y,two.y+2*(one.y-two.y)};
		this.diam=new Polygon(X,Y,4);
	}
	
	@Override
	public Point getLoc() {
		return this.diam.getBounds().getLocation();
	}
	
	@Override
	public void setLoc(Point p) {
		this.diam.getBounds().setLocation(p);
	}
	
	@Override
	public void translate(int x, int y) {
		this.diam.translate(x, y);
		one.translate(x, y);
		two.translate(x, y);
	}
	
	@Override
	public Rectangle getBounds() {
		return this.diam.getBounds();
	}
	
	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visitDiamond(this);
	}
	
	public Polygon getPolygon() {
		return this.diam;
	}
	
	@Override
	public void grow(boolean width, boolean height, int growingSpeed) {
		if(width) {
			this.two.x+=growingSpeed;
		}
		if(height) {
			this.one.y+=growingSpeed;
		}
		int X[]= {one.x,two.x,one.x+2*(two.x-one.x),two.x};
		int Y[]= {one.y,two.y,one.y,two.y+2*(one.y-two.y)};
		this.diam=new Polygon(X,Y,4);
	}
	
	@Override
	public void shrink(boolean width, boolean height, int growingSpeed) {
		if(width) {
			if(two.x>one.x) {
				this.two.x-=growingSpeed;
			}
		}
		if(height) {
			if(one.y>two.y) {
				this.one.y-=growingSpeed;
			}
		}
		int X[]= {one.x,two.x,one.x+2*(two.x-one.x),two.x};
		int Y[]= {one.y,two.y,one.y,two.y+2*(one.y-two.y)};
		this.diam=new Polygon(X,Y,4);
	}

	@Override
	public void setSize(int dw, int dh) {
		// TODO Auto-generated method stub
		
	}
	
	public int getWidth() {
		return (int) this.getBounds().getWidth();
	}

	public int getHeight() {
		return (int) this.getBounds().getHeight();
	}
}