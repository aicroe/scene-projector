package org.example.representation;

import java.util.ArrayList;
import java.util.List;

import org.example.matlib.Point;

/**
 * 
 * @author diegogarcia
 *
 */
public class PolygonMesh {
	
	private List<Vertex> vertices;
	private List<Edge> edges;
	private List<Polygon> polygons;
	private Point gravityCenter;
	
	public PolygonMesh() {
		
		vertices = new ArrayList<>();
		edges = new ArrayList<>();
		polygons = new ArrayList<>();
	}
	
	public List<Vertex> getVertices() {
		
		return vertices;
	}
	
	public List<Edge> getEdges() {
		
		return edges;
	}
	
	public void setGravityCenter(Point gc) {
		
		gravityCenter = gc;
	}
	
	public Point getGravityCenter() {
		
		return gravityCenter;
	}
	
	public void addVertex(Vertex v) {
		
		vertices.add(v);
		v.setIndex(vertices.size() - 1);
	}
	
	public void createEdge(int u, int v) {
		
		Edge e = new Edge(u, v);
		edges.add(e);
		e.setIndex(edges.size() - 1);
	}
	
	public Edge getEdge(int u, int v) {
		
		for (Edge edge: edges) {
			if (edge.isEquals(u, v)) {
				return edge;
			}
		}
		return null;
	}
	
	// insertar los vertices en sentido horario al observador
	public boolean createPolygon(int... v) {
		
		if (v.length < 3) return false;
		Polygon p = new Polygon();
		p.addVertex(vertices.get(0));
		for (int i = 0, j = 1; j< v.length; i++, j++) {
			Edge e = getEdge(v[i], v[j]);
			if (e != null) {
				p.addVertex(vertices.get(v[j]));
				p.addEdge(e.getIndex());
			} else {
				System.out.println("Fallo al crear poligono");
				return false;
			}
		}
		polygons.add(p);
		p.setIndex(polygons.size() -1);
		return true;
	}
	
}
