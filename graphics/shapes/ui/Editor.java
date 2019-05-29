package graphics.shapes.ui;

import graphics.Menu.MenuBar;
import graphics.shapes.SArrow;
import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SDiamond;
import graphics.shapes.SEllipse;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.STriangle;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.ImageAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;

import javax.swing.JFrame;

public class Editor extends JFrame
{
	
	private static final long serialVersionUID = 1L;
	
	public static ShapesView sview;
	public static SCollection model;
	private MenuBar menu;
	
	public Editor() throws IOException
	{	
		super("Shapes Editor");

		this.addWindowListener(new java.awt.event.WindowAdapter()
		{
			public void windowClosing(java.awt.event.WindowEvent evt)
			{
				System.exit(0);
			}
		});
		
		this.buildModel();
		
		Editor.sview = new ShapesView(Editor.model);
		Editor.sview.setPreferredSize(new Dimension(1200,600));
		this.getContentPane().add(Editor.sview, java.awt.BorderLayout.CENTER);
		
		this.menu = new MenuBar();
		this.setJMenuBar(menu.getMenu());
	}

	
	private void buildModel() throws IOException
	{
		Editor.model = new SCollection();
		Editor.model.addAttributes(new SelectionAttributes());

		SRectangle r = new SRectangle(new Point(700,500),60,40);
		r.addAttributes(new ColorAttributes(true,false,false,Color.MAGENTA,Color.MAGENTA,null,null,null));
		r.addAttributes(new SelectionAttributes());
		r.addAttributes(new ImageAttributes());
		Editor.model.add(r);
		
		SCircle c = new SCircle(new Point(100,100),70);
		c.addAttributes(new ColorAttributes(true,true,false,Color.BLACK,Color.BLUE,null,null,null));
		c.addAttributes(new SelectionAttributes());
		Editor.model.add(c);
		
		STriangle tri = new STriangle(new Point(100,100),new Point(150,150),new Point(50,200));
		tri.addAttributes(new ColorAttributes(true,true,false,Color.BLACK,Color.BLUE,null,null,null));
		tri.addAttributes(new SelectionAttributes());
		Editor.model.add(tri);
		
		SEllipse el = new SEllipse(new Point(100,100),100,200);
		el.addAttributes(new ColorAttributes(true,true,false,Color.BLACK,Color.BLUE,null,null,null));
		el.addAttributes(new SelectionAttributes());
		Editor.model.add(el);
		
		SArrow arrow = new SArrow(new Point(200,200),100,200);
		arrow.addAttributes(new ColorAttributes(true,true,false,Color.green,Color.BLUE,null,null,null));
		arrow.addAttributes(new SelectionAttributes());
		Editor.model.add(arrow);
		
		SDiamond dia = new SDiamond(new Point(100,100),new Point(200,200));
		dia.addAttributes(new ColorAttributes(true,true,false,Color.BLACK,Color.BLUE,null,null,null));
		dia.addAttributes(new SelectionAttributes());
		Editor.model.add(dia);
		
		SText t1= new SText(new Point(100,400),"hello");
		t1.addAttributes(new ColorAttributes(true,true,false,Color.YELLOW,Color.BLUE,null,null,null));
		t1.addAttributes(new FontAttributes());
		t1.addAttributes(new SelectionAttributes());
		Editor.model.add(t1);

		SCollection sc = new SCollection();
		sc.addAttributes(new SelectionAttributes());
		
		SCircle circle = new SCircle(new Point(350,300),30);
		circle.addAttributes(new ColorAttributes(true,true,false,Color.BLUE,Color.DARK_GRAY,null,null,null));
		circle.addAttributes(new SelectionAttributes());
		sc.add(circle);
		
		SRectangle r1 = new SRectangle(new Point(210,210),60,40);
		r1.addAttributes(new ColorAttributes(false,true,false,Color.BLUE,Color.BLUE,null,null,null));
		r1.addAttributes(new SelectionAttributes());
		r1.addAttributes(new ImageAttributes());
		sc.add(r1);
		
		Editor.model.add(sc);
		
	}
	
	
	public static void main(String[] args) throws IOException
	{
		Editor self = new Editor();
		self.pack();
		self.setVisible(true);
		
	}
}
