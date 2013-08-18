package AIPRM;

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import java.awt.Container;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Obstacle {

private Rectangle2D rect; 
	
	public Obstacle(double x, double y, double w, double h) {
		this.rect = new Rectangle2D.Double(x, y, w, h);
	}
	
	public Obstacle(String str) {
		String[] tokens = str.trim().split("\\s+");
		List<Double> xs = new ArrayList<Double>();
		List<Double> ys = new ArrayList<Double>();
		for (int i = 0; i < 4; i++) {
			xs.add(Double.valueOf(tokens[i*2]));
			ys.add(Double.valueOf(tokens[i*2+1]));
		}
		double xMin = Collections.min(xs);
		double xMax = Collections.max(xs);
		double yMin = Collections.min(ys);
		double yMax = Collections.max(ys);
		this.rect = new Rectangle2D.Double(xMin, yMin, xMax-xMin, yMax-yMin);
	}
	
	public boolean CheckCollision(double[] coord)
	{
		boolean collided = false;
		
		if(this.rect != null)
		{
			if(coord[0] >= this.rect.getX() && coord[0] <= this.rect.getX() + this.rect.getWidth())
			{
				if(coord[1] >= this.rect.getY() && coord[1] <= this.rect.getY() + this.rect.getHeight())
				{
					collided = true;
				}
			}
			
			return collided;
		}
		else
		{
			return false;
		}
		
	}
	
	public Rectangle2D getRect() {
		return (Rectangle2D)rect.clone();
	}
}
