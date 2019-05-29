package graphics.shapes;

import graphics.shapes.SArrow;
import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SDiamond;
import graphics.shapes.SEllipse;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.STriangle;

public interface ShapeVisitor {
	
	public void visitRectangle(SRectangle rectangle);
	public void visitCircle(SCircle circle);
	public void visitText(SText text);
	public void visitCollection(SCollection collection);
	public void visitTriangle(STriangle tria);
	public void visitDiamond(SDiamond sDiamond);
	public void visitEllipse(SEllipse sEllipse);
	public void visitSArrow(SArrow sArrow);
	
}