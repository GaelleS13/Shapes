
package graphics.shapes.attributes;

import java.awt.Color;

public class ColorAttributes extends Attributes {
	public static String ID = "color";
	private boolean filled;
	private boolean stroked;
	private boolean grad;
	private Color filledColor;
	private Color strokedColor;
	private Color gradStart;
	private Color gradEnd;
	private String direction;

	public ColorAttributes(boolean fill, boolean strok, boolean grad, Color filled, Color stroked, Color start,
			Color end,String direction) {
		this.filled = fill;
		this.stroked = strok;
		this.filledColor = filled;
		this.strokedColor = stroked;
		this.grad = grad;
		this.gradEnd = end;
		this.gradStart = start;
		this.direction=direction;
	}

	public String getId() {
		return ColorAttributes.ID;

	}

	public boolean isFilled() {
		return filled;

	}

	public boolean isStroked() {
		return stroked;

	}

	public boolean isGrad() {
		return grad;
	}

	public Color getFilledColor() {
		return filledColor;

	}

	public Color getStrokedColor() {
		return strokedColor;

	}

	public Color getStart() {
		return gradStart;
	}

	public Color getEnd() {
		return gradEnd;
	}
	public String getDirection() {
		return direction;
	}

}

