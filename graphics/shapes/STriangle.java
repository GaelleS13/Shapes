package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import graphics.shapes.ui.ShapesController;


public class STriangle extends Shape {
	
	private Polygon tria;
	private Point p1;
	private Point p2;
	private Point p3;

	public STriangle(Point p1,Point p2,Point p3) {
		int x[] = {p1.x,p2.x,p3.x};
		int y[] = {p1.y,p2.y,p3.y};
		this.tria=new Polygon(x,y,3);
		this.p1=p1;
		this.p2=p2;
		this.p3=p3;
	}
	
	public Polygon getTriangle() {
		return tria;
	}
		
	@Override
	public Point getLoc() {
		// TODO Auto-generated method stub
		return tria.getBounds().getLocation();
	}

	@Override
	public void setLoc(Point pt) {
		this.tria.xpoints[0]=pt.x;
		this.tria.ypoints[0]=pt.y;
	}

	@Override
	public void translate(int dx, int dy) {
		this.tria.translate(dx, dy);
	}

	@Override
	public Rectangle getBounds() {
		return this.tria.getBounds();
	}

	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitTriangle(this);
	}

	@Override
	public void setSize(int dw, int dh) {
		if(ShapesController.r2) {
			tria.xpoints[2]=tria.xpoints[2]+dw;
			tria.ypoints[1]=tria.ypoints[1]+dh;	
		}
		if(ShapesController.r3) {
			tria.xpoints[2]=tria.xpoints[2]+dw;
		}
		if(ShapesController.r4) {
			tria.ypoints[1]=tria.ypoints[1]+dh;	
		}
		this.tria=new Polygon(tria.xpoints,tria.ypoints,3);
	}

	@Override
	public void grow(boolean width, boolean height, int growingSpeed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shrink(boolean width, boolean height, int growingSpeed) {
		// TODO Auto-generated method stub
		
	}
	
	public Point getP1() {
		return this.p1;
	}
	public Point getP2() {
		return this.p2;
	}
	public Point getP3() {
		return this.p3;
	}

}
