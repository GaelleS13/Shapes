package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class SEllipse extends Shape {
	private Ellipse2D elli;
	
	private Point loc;
	
	private int width;
	
	private int height;
	
	public SEllipse(Point loc, int width, int height) {
		this.width=width;
		this.height=height;
		this.loc=loc;
		this.elli=new Ellipse2D.Double(this.loc.x, this.loc.y, this.width, this.height);
	}
	
	public Point getLoc() {
		return elli.getBounds().getLocation();
	}
	
	public void setLoc(Point p) {
		this.loc=p;
		this.elli=new Ellipse2D.Double(this.loc.x, this.loc.y, this.width, this.height);
	}
	
	public void translate(int x, int y) {
		this.loc.translate(x, y);
		this.elli=new Ellipse2D.Double(this.loc.x, this.loc.y, this.width, this.height);
	}
	
	public Rectangle getBounds() {
		return this.elli.getBounds();
	}
	
	public void accept(ShapeVisitor visitor) {
		visitor.visitEllipse(this);
	}
	
	public void grow(boolean width, boolean height, int growingSpeed) {
		if(width) {
			this.width+=growingSpeed;
			this.elli=new Ellipse2D.Double(this.loc.x, this.loc.y, this.width, this.height);
		}
		if(height) {
			this.height+=growingSpeed;
			this.elli=new Ellipse2D.Double(this.loc.x, this.loc.y, this.width, this.height);
		}
	}
	
	public void shrink(boolean width, boolean height, int growingSpeed) {
		if(width) {
			if(this.width>0) {
				this.width-=growingSpeed;
				this.elli=new Ellipse2D.Double(this.loc.x, this.loc.y, this.width, this.height);
			}
		}
		if(height) {
			if(this.height>0) {
				this.height-=growingSpeed;
				this.elli=new Ellipse2D.Double(this.loc.x, this.loc.y, this.width, this.height);
			}
		}
	}

	@Override
	public void setSize(int dw, int dh) {
		// TODO Auto-generated method stub
		
	}
	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}
}
