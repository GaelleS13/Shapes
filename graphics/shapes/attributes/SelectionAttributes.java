package graphics.shapes.attributes;

public class SelectionAttributes extends Attributes {

	public static final String ID = "Selection";
	private boolean selected;
	
	public SelectionAttributes() {
		this.selected=false;
	}

	@Override
	public String getId() {
		return ID;
	}

	public boolean isSelected() {
		return this.selected;
	}

	public void select() {
		this.selected = true;
	}

	public void unselect() {
		this.selected = false;
	}

	public void toggleSelection() {
		this.selected=!this.selected;
	}
	
}
