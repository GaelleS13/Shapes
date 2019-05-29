package graphics.shapes.ui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Iterator;

import graphics.shapes.SArrow;
import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SDiamond;
import graphics.shapes.SEllipse;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.STriangle;
import graphics.shapes.Shape;
import graphics.shapes.ShapeVisitor;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.ImageAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class ShapeDraftman implements ShapeVisitor {

	public ColorAttributes DEFAULTCOLORATTRIBUTES = new ColorAttributes(true, true, false, Color.BLACK, Color.BLACK,
			null, null, null);
	public static Graphics g;
	private static final int SELECTION = 8;
	private Rectangle bounds;
	private int locX;
	private int locY;
	private int rectangleWidth;
	private int rectangleHeight;
	private int radius;

	public ShapeDraftman(Graphics g) {
		ShapeDraftman.g = g;
	}

	@Override
	public void visitRectangle(SRectangle rectangle) {

		ColorAttributes colorAttributes = (ColorAttributes) rectangle.getAttributes(ColorAttributes.ID);
		SelectionAttributes selectionAttributes = (SelectionAttributes) rectangle.getAttributes(SelectionAttributes.ID);
		ImageAttributes imageAttributes = (ImageAttributes) rectangle.getAttributes(ImageAttributes.ID);

		this.bounds = rectangle.getBounds();
		this.locX = (int) bounds.getX();
		this.locY = (int) bounds.getY();
		this.rectangleWidth = (int) bounds.getWidth();
		this.rectangleHeight = (int) bounds.getHeight();

		if (colorAttributes.isFilled()) {
			drawFilledRectangle(rectangle);
		}
		if (colorAttributes.isGrad()) {
			Graphics2D graph = drawGrad(rectangle);
			graph.fillRect(rectangle.getLoc().x, rectangle.getLoc().y, rectangle.getRect().width, rectangle.getRect().height);
		}
		if (colorAttributes.isStroked()) {
			drawStrokedRectangle(rectangle);
		}

		if (selectionAttributes.isSelected()) {
			this.drawSelection(rectangle);
		}

		if (imageAttributes.isImage()) {
			ShapeDraftman.g.drawImage(imageAttributes.getImage(), rectangle.getLoc().x, rectangle.getLoc().y,
					rectangle.getBounds().width, rectangle.getBounds().height, null);
		}

	}

	@Override
	public void visitCircle(SCircle circle) {

		ColorAttributes colorAttributes = (ColorAttributes) circle.getAttributes(ColorAttributes.ID);
		SelectionAttributes selectionAttributes = (SelectionAttributes) circle.getAttributes(SelectionAttributes.ID);

		this.radius = circle.getRadius();
		this.locX = circle.getLoc().x;
		this.locY = circle.getLoc().y;
		this.bounds = circle.getBounds();

		if (colorAttributes.isFilled()) {
			drawFilledCircle(circle);
		}
		if (colorAttributes.isGrad()) {
			Graphics2D graph = drawGrad(circle);
			graph.fillOval(circle.getLoc().x, circle.getLoc().y, 2 * circle.getRadius(),
					2 * circle.getRadius());
		}
		if (colorAttributes.isStroked()) {
			drawStrokedCircle(circle);
		}

		if (selectionAttributes.isSelected()) {
			this.drawSelection(circle);
		}

	}

	@Override
	public void visitText(SText text) {

		FontAttributes fontAttributes = (FontAttributes) text.getAttributes(FontAttributes.ID);
		ColorAttributes colorAttributes = (ColorAttributes) text.getAttributes(ColorAttributes.ID);
		SelectionAttributes selectionAttributes = (SelectionAttributes) text.getAttributes(SelectionAttributes.ID);

		g.setFont(fontAttributes.font);

		this.locX = text.getLoc().x;
		this.locY = text.getLoc().y;

		this.bounds = text.getBounds();

		if (colorAttributes.isFilled()) {
			g.setColor(colorAttributes.getFilledColor());
			g.fillRect((int) bounds.x, (int) bounds.y, (int) bounds.width, (int) bounds.height);
		}
		if (colorAttributes.isGrad()) {
			Graphics2D graph = drawGrad(text);
			graph.fillRect(bounds.getLocation().x, bounds.getLocation().y, bounds.width, bounds.height);
		}
		
		g.setColor(colorAttributes.getStrokedColor());
		g.drawString(text.getText(), locX, locY);

		if (selectionAttributes.isSelected()) {
			this.drawSelection(text);
		}

	}

	@Override
	public void visitTriangle(STriangle tria) {
		ColorAttributes colorAttributes = (ColorAttributes) tria.getAttributes(ColorAttributes.ID);
		SelectionAttributes s = (SelectionAttributes) tria.getAttributes(SelectionAttributes.ID);
		if (colorAttributes.isFilled()) {
			ShapeDraftman.g.setColor(colorAttributes.getFilledColor());
			ShapeDraftman.g.fillPolygon(tria.getTriangle());
		}
		if (colorAttributes.isGrad()) {
			Graphics2D graph = drawGrad(tria);
			graph.fillPolygon(tria.getTriangle());
		}
		if (colorAttributes.isStroked()) {
			ShapeDraftman.g.setColor(colorAttributes.getStrokedColor());
			ShapeDraftman.g.drawPolygon(tria.getTriangle());
		}

		if (s.isSelected()) {
			this.drawSelection(tria);
		}
	}

	@Override
	public void visitEllipse(SEllipse elli) {
		ColorAttributes colorAttributes = (ColorAttributes) elli.getAttributes(ColorAttributes.ID);
		if (colorAttributes == null) {
			colorAttributes = DEFAULTCOLORATTRIBUTES;
		}
		if (colorAttributes.isFilled()) {
			ShapeDraftman.g.setColor(colorAttributes.getFilledColor());
			ShapeDraftman.g.fillOval(elli.getLoc().x, elli.getLoc().y, elli.getBounds().width, elli.getBounds().height);
		}
		if (colorAttributes.isGrad()) {
			Graphics2D graph = drawGrad(elli);
			graph.fillOval(elli.getLoc().x, elli.getLoc().y, elli.getBounds().width, elli.getBounds().height);
		}
		if (colorAttributes.isStroked()) {
			ShapeDraftman.g.setColor(colorAttributes.getStrokedColor());
			ShapeDraftman.g.drawOval(elli.getLoc().x, elli.getLoc().y, elli.getBounds().width, elli.getBounds().height);
		}
		SelectionAttributes s = (SelectionAttributes) elli.getAttributes(SelectionAttributes.ID);
		if (s == null) {
			s = new SelectionAttributes();
			;
			elli.addAttributes(s);
		}
		if (s.isSelected()) {
			this.drawSelection(elli);
		}
	}

	@Override
	public void visitDiamond(SDiamond diam) {
		ColorAttributes colorAttributes = (ColorAttributes) diam.getAttributes(ColorAttributes.ID);
		if (colorAttributes == null) {
			colorAttributes = DEFAULTCOLORATTRIBUTES;
		}
		if (colorAttributes.isFilled()) {
			ShapeDraftman.g.setColor(colorAttributes.getFilledColor());
			ShapeDraftman.g.fillPolygon(diam.getPolygon());
		}
		if (colorAttributes.isGrad()) {
			Graphics2D graph = drawGrad(diam);
			graph.fillPolygon(diam.getPolygon());
		}
		if (colorAttributes.isStroked()) {
			ShapeDraftman.g.setColor(colorAttributes.getStrokedColor());
			ShapeDraftman.g.drawPolygon(diam.getPolygon());
		}
		SelectionAttributes s = (SelectionAttributes) diam.getAttributes(SelectionAttributes.ID);
		if (s == null) {
			s = new SelectionAttributes();
			;
			diam.addAttributes(s);
		}
		if (s.isSelected()) {
			this.drawSelection(diam);
		}
	}

	@Override
	public void visitSArrow(SArrow arrow) {
		ColorAttributes colorAttributes = (ColorAttributes) arrow.getAttributes(ColorAttributes.ID);
		SelectionAttributes selectionAttributes = (SelectionAttributes) arrow.getAttributes(SelectionAttributes.ID);

		if (colorAttributes == null) {
			colorAttributes = DEFAULTCOLORATTRIBUTES;
		}
		if (colorAttributes.isFilled()) {
			ShapeDraftman.g.setColor(colorAttributes.getFilledColor());
			ShapeDraftman.g.fillPolygon(arrow.getPoly());
		}
		if (colorAttributes.isGrad()) {
			Graphics2D graph = drawGrad(arrow);
			graph.fillPolygon(arrow.getPoly());
		}
		if (colorAttributes.isStroked()) {
			ShapeDraftman.g.setColor(colorAttributes.getStrokedColor());
			ShapeDraftman.g.drawPolygon(arrow.getPoly());
		}

		if (selectionAttributes.isSelected()) {
			this.drawSelection(arrow);
		}
	}

	@Override
	public void visitCollection(SCollection collection) {
		for (Iterator<Shape> it = collection.iterator(); it.hasNext();) {
			Shape shape = it.next();
			SelectionAttributes selectionAttributes = (SelectionAttributes) shape.getAttributes(SelectionAttributes.ID);
			if (shape instanceof SCollection && selectionAttributes.isSelected()) {
				this.drawSelection(shape);
			}
			shape.accept(this);
		}
	}

	public void drawFilledRectangle(SRectangle rectangle) {
		ColorAttributes colorAttributes = (ColorAttributes) rectangle.getAttributes(ColorAttributes.ID);
		g.setColor(colorAttributes.getFilledColor());
		g.fillRect(locX, locY, rectangleWidth, rectangleHeight);
	}

	public void drawStrokedRectangle(SRectangle rectangle) {
		ColorAttributes colorAttributes = (ColorAttributes) rectangle.getAttributes(ColorAttributes.ID);
		g.setColor(colorAttributes.getStrokedColor());
		g.drawRect(locX, locY, rectangleWidth, rectangleHeight);
	}

	public void drawFilledCircle(SCircle circle) {
		ColorAttributes colorAttributes = (ColorAttributes) circle.getAttributes(ColorAttributes.ID);
		g.setColor(colorAttributes.getFilledColor());
		g.fillOval(locX, locY, 2 * radius, 2 * radius);
	}

	public void drawStrokedCircle(SCircle circle) {
		ColorAttributes colorAttributes = (ColorAttributes) circle.getAttributes(ColorAttributes.ID);
		g.setColor(colorAttributes.getStrokedColor());
		g.drawOval(locX, locY, 2 * radius, 2 * radius);
	}

	public void highlightText(SText text) {
		ColorAttributes colorAttributes = (ColorAttributes) text.getAttributes(ColorAttributes.ID);
		g.setColor(colorAttributes.getFilledColor());
		g.fillRect((int) bounds.x, (int) bounds.y, (int) bounds.width, (int) bounds.height);
	}

	public void drawText(SText text) {
		ColorAttributes colorAttributes = (ColorAttributes) text.getAttributes(ColorAttributes.ID);
		g.setColor(colorAttributes.getStrokedColor());
		g.drawString(text.getText(), locX, locY);
	}

	public void drawSelection(Shape shape) {
		this.bounds = shape.getBounds();
		g.setColor(Color.black);
		g.drawRect((int) bounds.x - SELECTION, (int) bounds.y - SELECTION, SELECTION, SELECTION);
		g.drawRect((int) (bounds.x + bounds.width), (int) bounds.y - SELECTION, SELECTION, SELECTION);
		g.drawRect((int) (bounds.x + bounds.width), (int) (bounds.y + bounds.height), SELECTION, SELECTION);
		g.drawRect((int) bounds.x - SELECTION, (int) (bounds.y + bounds.height), SELECTION, SELECTION);
	}
	
	public Graphics2D drawGrad(Shape shape) {
		this.bounds=shape.getBounds();
		ColorAttributes colorAttributes = (ColorAttributes) shape.getAttributes(ColorAttributes.ID);
		if (colorAttributes.getDirection() == "To the bottom") {
			GradientPaint gradient = new GradientPaint(bounds.x + (bounds.width / 2),
					bounds.y, colorAttributes.getStart(), bounds.x + (bounds.width / 2),
					bounds.y + bounds.height, colorAttributes.getEnd());
			Graphics2D g2d = (Graphics2D) g;
			g2d.setPaint(gradient);
			return g2d;
		}
		if (colorAttributes.getDirection() == "To the top") {
			GradientPaint gradient = new GradientPaint(bounds.x + (bounds.width / 2),
					bounds.y + bounds.height, colorAttributes.getStart(),
					bounds.x + (bounds.width / 2), bounds.y, colorAttributes.getEnd());
			Graphics2D g2d = (Graphics2D) g;
			g2d.setPaint(gradient);
			return g2d;
		}
		if (colorAttributes.getDirection() == "To the right") {
			GradientPaint gradient = new GradientPaint(bounds.x + bounds.width,
					bounds.y + (bounds.height / 2), colorAttributes.getStart(), bounds.x,
					bounds.y + (bounds.height / 2), colorAttributes.getEnd());
			Graphics2D g2d = (Graphics2D) g;
			g2d.setPaint(gradient);
			return g2d;
		}
		if (colorAttributes.getDirection() == "To the left") {
			GradientPaint gradient = new GradientPaint(bounds.x,
					bounds.y + (bounds.height / 2), colorAttributes.getStart(),
					bounds.x + bounds.width, bounds.y + (bounds.height / 2),
					colorAttributes.getEnd());
			Graphics2D g2d = (Graphics2D) g;
			g2d.setPaint(gradient);
			return g2d;
		}
		return null;
	}


}
