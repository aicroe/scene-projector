package org.example.framework;

import org.example.matlib.Matrix;
import org.example.matlib.Point;
import org.example.representation.Vertex;

/**
 * 
 * @author diegogarcia
 *
 */
public class FrontalObserver extends Observer {

	public FrontalObserver(World world) {
		
		super(World.k, world);
	}
	
	/*
	@Override
	public Matrix window2viewport() {
		
		Matrix m = new Matrix(4, 4);
		Viewport viewport = getViewport();
		Window window = getWindow();
		double umin = viewport.getumin();
		double vmin = viewport.getvmin();
		double umax = viewport.getumax();
		double vmax = viewport.getvmax();
		double xmin = window.getxmin();
		double ymin = window.getymin();
		double xmax = window.getxmax();
		double ymax = window.getymax();
		double sx = (umax - umin) / (xmax - xmin);
		double sy = (vmax - vmin) / (ymax - ymin);
		m.set(0, 0, sx);
		m.set(1, 1, sy);
		m.set(3, 0, -sx * xmin + umin);
		m.set(3, 1, -sy * ymin + vmin);
		m.set(3, 3, 1);
		return m;
	}*/

	@Override
	public void projectVertex(Vertex vertex, Matrix m) {
		
		vertex.applyPF(m);
	}

	@Override
	public Point getViewportPoint(Vertex vertex) {
		
		return vertex.getvf();
	}

}
