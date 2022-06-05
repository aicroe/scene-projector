package org.example.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
 * @author diegogarcia
 *
 */
public class MessageBox extends BufferedImage {
	
	private Point origin;
	private Graphics g;
	private List<String> strings;
	
	private int offset;
	private int range;
	
	public MessageBox(int ox, int oy, int _width, int _height) {
		
		super(_width, _height, TYPE_INT_RGB);
		origin = new Point(ox, oy);
		strings = new CopyOnWriteArrayList<>();
		offset = 0;
		range = 0;
		g = createGraphics();
	}
	
	public int getox() {
		
		return origin.x;
	}
	
	public int getoy() {
		
		return origin.y;
	}
	
	public void setRange(int r) {
		
		range = r;
	}
	
	public void moveOffset(int i) {
		
		if (i > 0 && offset + 1 + range < strings.size() ) {
			offset++;
		} else  if (i < 0 && offset -1 >= 0){
			offset--;
		}
	}
	
	public void addString(String str) {
		
		strings.add(str);
		if (strings.size() - offset > range) {
			offset++;
		}
	}

	public void updateGraphics() {
		
		int width = getWidth();
		int height = getHeight();
		g.setColor(Color.WHITE);
		Graphiclib.fillRect(g, 0, 0, width, height);
		g.setColor(Color.BLACK);
		Graphiclib.drawRect(g, 0, 0, width -1, height - 1);
		int step = 15;
		int len = Math.min(strings.size(), offset + range);
		for (int i = offset, j = 15; i< len; i++, j += step) {
			g.drawString(strings.get(i), 5, j);
		}
	}
}
