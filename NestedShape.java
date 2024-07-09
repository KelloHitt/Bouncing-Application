/*
 * ============================================================================================
 * NestedShape.java: extends RectangleShape class and creates a NestedShape object.
 * The NestedShape object can create and contain inner shapes
 * ============================================================================================
 */
import java.awt.Color;
import java.awt.*;
import java.util.*;
import java.util.*;
import java.lang.*;
class NestedShape extends RectangleShape{
	private ArrayList<Shape> innerShapes = new ArrayList<Shape>();
	
	public Shape createInnerShape(PathType pt, ShapeType st) {
		if (st == ShapeType.SQUARE) {
		    int sideLength = (Math.min(this.width, this.height))/ 4;
		    SquareShape innerShape = new SquareShape(0, 0, sideLength, width, height, this.color, this.borderColor, pt);
			innerShapes.add(innerShape);
			innerShape.parent = this;
		    
		}
		else if (st == ShapeType.RECTANGLE){
			RectangleShape innerShape = new RectangleShape(0, 0, (width / 4), (height / 4),  width, height, this.color, this.borderColor, pt);
			innerShapes.add(innerShape);
			innerShape.parent = this;
			
		}
		else if (st ==  ShapeType.NESTED) {
		    NestedShape innerShape = new NestedShape(0, 0, width / 4, height / 4,  width, height, this.color, this.borderColor, pt);
		    innerShapes.add(innerShape);
		    innerShape.parent = this;

		}
		return innerShapes.get(innerShapes.size() - 1);
	}
	
	public NestedShape(int x, int y, int width, int height, int panelWidth, int panelHeight, Color fill, Color borderColour, PathType pt) {
		super(x, y, width, height, panelWidth, panelHeight, fill, borderColour, pt);
		createInnerShape(PathType.BOUNCING, ShapeType.RECTANGLE);
	}
	
	public NestedShape(int width, int height) {
		super(0, 0, width, height, Shape.DEFAULT_PANEL_WIDTH, Shape.DEFAULT_PANEL_HEIGHT, Shape.DEFAULT_COLOR, Shape.DEFAULT_BORDER_COLOR,  PathType.BOUNCING);
	}
	
	
	public Shape getInnerShapeAt(int index) {
		return innerShapes.get(index);
	}
	
	public int getSize() {
		return innerShapes.size();
	}
	
	//Q3 METHODS
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		g.translate(x, y);
		for (Shape shape: innerShapes) {
			shape.draw(g);
			shape.drawString(g);

			}
		g.translate(-x, -y);
	}
	
	public void move() {
		super.move();
		for (Shape shape: innerShapes) {
			shape.move();
		}
	}
	
	// Q4 methods
	public int indexOf(Shape s) {
		return innerShapes.indexOf(s);
	}
	
	public void addInnerShape(Shape s) {
		innerShapes.add(s);
		s.parent = this;
		
	}
	
	public void removeInnerShape(Shape s) {
		int indexCount = 0;
		for (Shape shape : innerShapes) {
			if (shape == s) {
				innerShapes.remove(indexCount);
				break;
			}
			indexCount+=1;
		}
		s.parent = null;
	}
	
	public void removeInnerShapeAt(int index) {
		Shape shape = innerShapes.remove(index);
		shape.parent = null;
		
	}
	
	public ArrayList<Shape> getAllInnerShapes(){
		return innerShapes;
	}
		
	
	
}
