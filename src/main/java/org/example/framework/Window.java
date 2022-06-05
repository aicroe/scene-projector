package org.example.framework;

/**
 * 
 * @author diegogarcia
 *
 */
public class Window {
	
	private double xmin;
	private double ymin;
	private double xmax;
	private double ymax;
	
	public Window(
			double _xmin, double _ymin, 
			double _xmax, double _ymax) {
		
		xmin = _xmin;
		ymin = _ymin;
		xmax = _xmax;
		ymax = _ymax;
	}

	public double getxmin() {
		
		return xmin;
	}

	public double getymin() {
		
		return ymin;
	}

	public double getxmax() {
		
		return xmax;
	}

	public double getymax() {
		
		return ymax;
	}
	
}
