package org.example.representation;

/**
 * 
 * @author diegogarcia
 *
 */
public class Edge {
	
	private int u;
	private int v;
	private int index;
	
	public Edge(int u, int v) {

		this.u = u;
		this.v = v;
		index = -1;
	}
	
	public int getIndex() {
		
		return index;
	}
	
	public void setIndex(int i) {
		
		index = i;
	}
	
	public int getU() {
		
		return u;
	}
	
	public int getV() {
		
		return v;
	}
	
	public boolean isEquals(int u, int v) {
		
		return (this.v == v && this.u == u) || 
				(this.v == u && this.u == v);
	}
	
	public int getNext(int i) {
		
		if (i == v) return u;
		else if (i == u) return v;
		return -1;
	}

}
