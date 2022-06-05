package org.example.framework;

import static org.example.matlib.Transformations.PP;

import org.example.matlib.Matrix;
import org.example.matlib.Point;
import org.example.representation.PolygonMesh;
import org.example.representation.Vertex;

/**
 * 
 * @author diegogarcia
 *
 */
public class PerspectiveObserver extends Observer {
	
	private double d;
	
	public PerspectiveObserver(double d, World world) {
		
		super(null, world);
		this.d = d;
	}
	
	@Override
	public void projectWorld() {
		
		Matrix pp = PP(d);
		Matrix w2v = window2viewport();
		Matrix m = pp.mul(w2v);
		for (PolygonMesh object : getWorld().getObjects()) {
			for (Vertex vertex : object.getVertices()) {
				projectVertex(vertex, m);
			}
		}

	}

	@Override
	public void projectVertex(Vertex vertex, Matrix m) {
		
		vertex.applyPP(m);
	}

	@Override
	public Point getViewportPoint(Vertex vertex) {
		
		
		return vertex.getvp();
	}

}
