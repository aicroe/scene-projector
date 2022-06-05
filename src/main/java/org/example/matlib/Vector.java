package org.example.matlib;

/**
 * 
 * @author diegogarcia
 *
 */
public class Vector extends Matrix {
	
	public Vector(double... v) {
		
		super(1, v.length + 1);
		for (int i = 0; i< v.length; i++) set(0, i, v[i]);
		set(0, v.length, 0);
	}
	
	public double get(int i) {
		
		return get(0, i);
	}
	
	public void set(int i, double v) {
		
		set(0, i, v);
	}
	
	@Override
	public Vector add(Matrix m) {
		
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Vector sub(Matrix m) {
		
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Vector mul(Matrix m) {
		
		Vector result = new Vector(0, 0, 0);
		mul(this, m, result);
		return result;
	}

}
