package org.example.framework;

import static org.example.matlib.Transformations.T3D;
import static org.example.matlib.Point.X;
import static org.example.matlib.Point.Y;
import static org.example.matlib.Point.Z;

import java.util.ArrayList;
import java.util.List;

import org.example.matlib.Matrix;
import org.example.matlib.Point;
import org.example.matlib.Vector;
import org.example.representation.PolygonMesh;
import org.example.representation.Vertex;

/**
 * 
 * @author diegogarcia
 *
 */
public class World {
	
	public static final Vector i = new Vector(1, 0, 0);
	public static final Vector j = new Vector(0, 1, 0);
	public static final Vector k = new Vector(0, 0, 1);
	public static final Point o = new Point(0, 0, 0);
	
	private List<PolygonMesh> objects;
	
	public World() {
		
		objects = new ArrayList<>();
	}
	
	public void addObject(PolygonMesh pm) {
		
		objects.add(pm);
	}

	public List<PolygonMesh> getObjects() {

		return objects;
	}
	
	public void apply(Matrix m) {
		
		for (PolygonMesh pm: objects) {
			for (Vertex v: pm.getVertices()) {
				v.apply(m);
			}
			pm.setGravityCenter(pm.getGravityCenter().mul(m));
		}
	}
	
	public void applyRelatedToCenter(Matrix m) {
		
		for (PolygonMesh pm: objects) {
			Point gc = pm.getGravityCenter();
			Matrix t = T3D(-gc.get(X), -gc.get(Y), -gc.get(Z));
			Matrix _t = T3D(gc.get(X), gc.get(Y), gc.get(Z));
			Matrix mm = t.mul(m).mul(_t);
			for (Vertex v: pm.getVertices()) {
				v.apply(mm);
			}
			//pm.setGravityCenter(pm.getGravityCenter().mul(mm));
		}
	}

}
