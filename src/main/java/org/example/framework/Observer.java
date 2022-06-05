package org.example.framework;

import static org.example.matlib.Point.X;
import static org.example.matlib.Point.Y;
import static org.example.matlib.Transformations.P3D;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import org.example.matlib.Matrix;
import org.example.matlib.Point;
import org.example.matlib.Vector;
import org.example.representation.Edge;
import org.example.representation.PolygonMesh;
import org.example.representation.Vertex;
import org.example.ui.Graphiclib;
import org.example.ui.Viewport;

/**
 * 
 * @author diegogarcia
 *
 */
public abstract class Observer {
	
	private Vector normal; // normal del plano de proyeccion
	private World world;
	private Window window;
	private Viewport viewport;
	
	public Observer(Vector normal, World world) {
		
		this.normal = normal;
		this.world = world;
	}
	
	public Vector getNormal() {
		
		return normal;
	}
	
	public Window getWindow() {
		
		return window;
	}
	
	public void setWindow(Window w) {
		
		window = w;
	}
	
	public Viewport getViewport() {
		
		return viewport;
	}
	
	public void setViewport(Viewport v) {
		
		viewport = v;
	}
	
	public World getWorld() {
		
		return world;
	}

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
	}
	
	public void projectWorld() {
		
		Matrix fp = P3D(getNormal());
		Matrix w2v = window2viewport();
		Matrix m = fp.mul(w2v);
		for (PolygonMesh object : getWorld().getObjects()) {
			for (Vertex vertex : object.getVertices()) {
				projectVertex(vertex, m);
			}
		}
	}
	
	public abstract void projectVertex(Vertex vertex, Matrix m);
	
	private void drawBackground() {
		
		Graphics g = viewport.getGraphics();
		g.setColor(Color.WHITE);
		Graphiclib.fillRect(g, 
				0, 0, 
				viewport.getWidth() - 1, 
				viewport.getHeight() - 1);
		g.setColor(Color.BLACK);
		Graphiclib.drawRect(g, 
				0, 0, 
				viewport.getWidth() - 1, 
				viewport.getHeight() - 1);
	}
	
	public void updateGraphics() {
		
		drawBackground();
		Graphics g = viewport.getGraphics();
		for (PolygonMesh object : world.getObjects()) {
			List<Vertex> vertices = object.getVertices();
			for (Edge edge : object.getEdges()) {
				Point u = getViewportPoint(vertices.get(edge.getU()));
				Point v = getViewportPoint(vertices.get(edge.getV()));
				if (u == null || v == null)
					continue;
				int u2x = viewport.getXBuffer(u.get(X));
				int u2y = viewport.getYBuffer(u.get(Y));
				int v2x = viewport.getXBuffer(v.get(X));
				int v2y = viewport.getYBuffer(v.get(Y));
				
				Graphiclib.drawLine(g, u2x, u2y, v2x, v2y);
			}
		}
	}
	
	public abstract Point getViewportPoint(Vertex vertex); 

}
