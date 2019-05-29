package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import graphics.shapes.attributes.ImageAttributes;

public class SRectangle extends Shape {
	
	private Rectangle rect;
	
	public SRectangle(Point loc,int width,int height) {
		this.rect = new Rectangle(loc.x , loc.y , width , height);
	}
	
	public Rectangle getRect() {
		return this.rect;
	}
	
	@Override
	public Point getLoc() {
		return this.rect.getLocation();
	}
	
	@Override
	public void setLoc(Point loc) {
		this.rect.setLocation(loc);;
	}

	@Override
	public void translate(int dx, int dy) {
		this.rect.translate(dx, dy);
	}

	@Override
	public Rectangle getBounds() {
		return this.rect.getBounds();
	}

	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitRectangle(this);
	}
	
	@Override
	public void setSize(int dw, int dh) {
		if(((ImageAttributes) this.getAttributes(ImageAttributes.ID)).isImage()){
			((ImageAttributes) this.getAttributes(ImageAttributes.ID)).setSize(dw);
		}
		else {
			rect.setSize(rect.width+dw, rect.height+dh);
		}
	}
	
	@Override
	public void grow(boolean width, boolean height, int growingSpeed) {
		if(width) {
			this.rect.width+=growingSpeed;
		}
		if(height) {
			this.rect.height+=growingSpeed;
		}
	}
	
	@Override
	public void shrink(boolean width, boolean height, int growingSpeed) {
		if(width) {
			if(this.rect.width>0) {
				this.rect.width-=growingSpeed;
			}
		}
		if(height) {
			if(this.rect.height>0) {
				this.rect.height-=growingSpeed;
			}
		}
	}

	
}
