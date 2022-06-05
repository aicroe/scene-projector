package org.example.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.example.matlib.Point;

/**
 * 
 * @author diegogarcia
 *
 */
public class Viewport extends BufferedImage { 
	
	// normalizado
	// u = (0, 0), v = (1, 1)
	// relativo a este buffer, asi que para graficar en la 
	// posicion correcta se necesita el origen para desplazar 
	// el viewport a la posicion correcta
	
	private int ox;
	private int oy;
	private Graphics g;
	
	public Viewport(
			int ox, int oy, int fx, int fy) {
		
		super(fx - ox, fy - oy, TYPE_INT_RGB);
		this.ox = ox;
		this.oy = oy;
		g = createGraphics();
	}
	
	public Graphics getGraphics() {
		
		return g;
	}
	
	public int getox() {
		
		return ox;
	}
	
	public int getoy() {
		
		return oy;
	}
	
	public double getumin() {
		
		return 0;
	}

	public double getvmin() {
		
		return 0;
	}

	public double getumax() {
		
		return 1;
	}

	public double getvmax() {
		
		return 1;
	}
	
	public int gettamx() {
		
		return getWidth();
	}
	
	public int gettamy() {
		
		return getHeight();
	}
	
	public int getXBuffer(double x) {
		
		return (int)(x * gettamx());
	}
	
	public int getYBuffer(double y) {

		return (int)((1 - y) * gettamy());
	}

	public boolean isInBounds(Point p) {

		double x = p.get(Point.X);
		double y = p.get(Point.Y);
		return x >= 0 && x <= 1 && y >= 0 && y <= 1;
	}

}
