package org.example.representation;

import static org.example.matlib.Point.X;
import static org.example.matlib.Point.Y;
import static org.example.matlib.Point.Z;
import static org.example.matlib.Point.W;

import org.example.matlib.Matrix;
import org.example.matlib.Point;

/**
 * 
 * @author diegogarcia
 *
 */
public class Vertex {
	
	//vertice 3D
	private Point v; // vertice
	//vertices 2D
	private Point vp; // vertice viewport perspectiva
	private Point vf; // vertice viewport frente
	private Point vs; // vertice viewport superficie
	private Point vl; // vertice viewport lateral
	
	private int index;
	
	public Vertex(int x, int y, int z) {
		
		v = new Point(x, y, z);
		index = -1;
	}
	
	public Point getV() {
		
		return v;
	}
	
	public Point getvp() {
		
		return vp;
	}
	
	public Point getvf() {
		
		return vf;
	}
	
	public Point getvs() {
		
		return vs;
	}
	
	public Point getvl() {
		
		return vl;
	}
	
	public int getIndex() {
		
		return index;
	}
	
	public void setIndex(int i) {
		
		index = i;
	}
	
	public void apply(Matrix m) {
		
		v = v.mul(m);
	}
	
	public void applyPP(Matrix m) {
		
		vp = v.mul(m);
		if (vp.get(W) < 1) {
			vp = null;
			return;
		}
		vp.scalarMul(1.0 / vp.get(W));
		vp = new Point(vp.get(X), vp.get(Y));
	}
	
	public void applyPF(Matrix m) {
		
		vf = v.mul(m);
		vf = new Point(vf.get(X), vf.get(Y));
	}
	
	public void applyPS(Matrix m) {
		
		vs = v.mul(m);
		vs = new Point(vs.get(X), vs.get(Z));
	}
	
	public void applyPL(Matrix m) {
		
		// para que el plano sea Z (horizontal) Y(vertical) 
		Point v = new Point(this.v.get(X), this.v.get(Y), this.v.get(Z));
		double tmp = v.get(Y);
		v.set(Y, v.get(Z));
		v.set(Z, tmp);
		vl = v.mul(m);
		vl = new Point(vl.get(Y), vl.get(Z));
	}

}
