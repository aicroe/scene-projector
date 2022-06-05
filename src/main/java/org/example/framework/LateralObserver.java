package org.example.framework;

import org.example.matlib.Matrix;
import org.example.matlib.Point;
import org.example.representation.Vertex;
import org.example.ui.Viewport;

/**
 * 
 * @author diegogarcia
 *
 */
public class LateralObserver extends Observer {

	public LateralObserver(World world) {
		
		super(World.i, world);
	}
	
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
		m.set(1, 1, sx);
		m.set(2, 2, sy);
		m.set(3, 1, -sx * xmin + umin);
		m.set(3, 2, -sy * ymin + vmin);
		m.set(3, 3, 1);
		return m;
	}

	@Override
	public void projectVertex(Vertex vertex, Matrix m) {
		
		vertex.applyPL(m);
	}

	@Override
	public Point getViewportPoint(Vertex vertex) {

		return vertex.getvl();
	}

}
