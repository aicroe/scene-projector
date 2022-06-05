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
public class SuperiorObserver extends Observer {
	
	public SuperiorObserver(World world) {
		
		super(World.j, world);
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
		m.set(0, 0, sx);
		m.set(2, 2, sy);
		m.set(3, 0, -sx * xmin + umin);
		m.set(3, 2, -sy * ymin + vmin);
		m.set(3, 3, 1);
		return m;
	}

	@Override
	public void projectVertex(Vertex vertex, Matrix m) {
		
		vertex.applyPS(m);
		
	}

	@Override
	public Point getViewportPoint(Vertex vertex) {
		
		return vertex.getvs();
	}
	
	/*
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
		double sz = (vmax - vmin) / (ymax - ymin);
		m.set(0, 0, sx);
		m.set(2, 2, sz);
		m.set(3, 0, -sx * xmin + umin);
		m.set(3, 2, -sz * ymin + vmin);
		m.set(3, 3, 1);
		return m;
	}
	
	@Override
	public void projectWorld() {
		
		Matrix sp = P3D(getNormal());
		Matrix w2v = window2viewport();
		Matrix m = sp.mul(w2v);
		for (PolygonMesh object : getWorld().getObjects()) {
			for (Vertex vertex : object.getVertices()) {
				vertex.applyPS(m);
			}
		}

	}

	@Override
	public void drawGraphics(Graphics g) {
		
		drawBackground(g);
		Viewport viewport = getViewport();
		for (PolygonMesh object : getWorld().getObjects()) {
			List<Vertex> vertices = object.getVertices();
			for (Edge edge : object.getEdges()) {
				Point u = vertices.get(edge.getU()).getvs();
				Point v = vertices.get(edge.getV()).getvs();
				int u2x = viewport.getXScreen(u.get(X));
				int u2y = viewport.getYScreen(u.get(Z));
				int v2x = viewport.getXScreen(v.get(X));
				int v2y = viewport.getYScreen(v.get(Z));
				Graphiclib.drawLine(g, u2x, u2y, v2x, v2y);
			}
		}

	}*/

}
