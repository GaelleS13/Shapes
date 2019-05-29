package graphics.shapes.attributes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import graphics.shapes.ui.ShapeDraftman;

public class FontAttributes extends Attributes {

	public static final String ID = "Font";
	private static final Font DEFAULT_FONT = new Font("Monaco",Font.BOLD,30);
	public Font font;
	public Color fontColor;
	
	public FontAttributes(){
		this.font = DEFAULT_FONT;
	}
	
	public FontAttributes(Font font) {
		this.font = font;

	}
	
	@Override
	public String getId() {
		return ID;
	}
	
	public Font getFont() {
		return this.font;
	}
	
	public Color getFontColor() {
		return this.fontColor;
	}
	
	public void setFont(Float s) {
		Font ft = ShapeDraftman.g.getFont();
		this.font= ft.deriveFont(ft.getSize()+s);
	}
	
	public Rectangle getBounds(String text) {
		
		FontMetrics fm = ShapeDraftman.g.getFontMetrics();
	    int height = fm.getHeight();
		int width = fm.stringWidth(text);
		int ascent = fm.getAscent();
		return new Rectangle(0,ascent,width,height);
	}
	
	public void grow(int growingSpeed) {
		Font old=this.font;
		this.font=new Font(old.getFontName(),old.getStyle(),old.getSize()+growingSpeed);
	}

	public void shrink(int growingSpeed) {
		Font old=this.font;
		if(old.getSize()>0) {
			this.font=new Font(old.getFontName(),old.getStyle(),old.getSize()-growingSpeed);
		}
	}


}
