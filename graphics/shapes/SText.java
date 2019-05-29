package graphics.shapes;

import graphics.shapes.attributes.FontAttributes;

import java.awt.Point;
import java.awt.Rectangle;

public class SText extends Shape {
	
	private String text;
	private Point loc;
	
	public SText(Point loc,String text) {
		this.text = new String(text);
		this.loc = new Point(loc);
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text=text;
	}

	@Override
	public Point getLoc() {
		return this.loc;
	}

	@Override
	public void setLoc(Point loc) {
		this.loc=loc;
	}

	@Override
	public void translate(int dx, int dy) {
		this.loc.translate(dx,dy);

	}

	@Override
	public Rectangle getBounds() {
		Rectangle fontBounds = ((FontAttributes) super.getAttributes(FontAttributes.ID)).getBounds(this.text);
		return new Rectangle(this.loc.x,this.loc.y-fontBounds.y,fontBounds.width,fontBounds.height);
	}

	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitText(this);
	}
	
	@Override
	public void setSize(int dw, int dh) {
		double d =(Math.sqrt(Math.pow(dw,2)+Math.pow(dh,2))/2);
		double c=(Math.sqrt(Math.pow(d,2)/2));
		float s=(float) (c*0.75);
		if(dw<0 || dh<0) {
			((FontAttributes) getAttributes("Font")).setFont(-s);
		}
		else {
			((FontAttributes) getAttributes("Font")).setFont(s);
		}
	}
	
	@Override
	public void grow(boolean width, boolean height, int growingSpeed) {
		FontAttributes f=(FontAttributes) this.getAttributes(FontAttributes.ID);
		f.grow(growingSpeed);
	}
	
	@Override
	public void shrink(boolean width, boolean height, int growingSpeed) {
		FontAttributes f=(FontAttributes) this.getAttributes(FontAttributes.ID);
		f.shrink(growingSpeed);
	}


}
