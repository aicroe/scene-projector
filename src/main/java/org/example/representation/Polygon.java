package org.example.representation;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author diegogarcia
 *
 */
public class Polygon {
	
	private List<Vertex> vertices;
	private List<Integer> edges;
	private int index;
	
	public Polygon() {
		
		vertices = new ArrayList<>();
		edges = new ArrayList<>();
		index = -1;
	}
	
	public int getIndex() {
		
		return index;
	}
	
	public void setIndex(int i) {
		
		index = i;
	}
	
	public List<Integer> getEdges() {
		
		return edges;
	}
	
	public void addVertex(Vertex v) {
		
		vertices.add(v);
	}
	
	public void addEdge(int e) {
		
		edges.add(e);
	}
	
	/*
	public boolean isValid() {

		int len = edges.size();
		if (len <  3) return false;
		List<Vertex> vertices = new ArrayList<>();
		for (Edge edge: edges) {
			if (!vertices.contains(edge.getU()))
				vertices.add(edge.getU());
			if (!vertices.contains(edge.getV()))
				vertices.add(edge.getV());
		}
		int[][] graph = new int[vertices.size()][vertices.size()];
		for (Edge edge: edges) {
			int u = vertices.indexOf(edge.getU());
			int v = vertices.indexOf(edge.getV());
			graph[u][v]++;
			graph[v][u]++;
		}
		boolean[] visited = new boolean[vertices.size()];
		return isValid(graph, 0, visited, 0, 0);
	}
	
	private boolean isValid(
			int[][] graph,
			int count, boolean[] visited, 
			int origin, int current) {

		count++;
		int len = graph.length;
		visited[current] = true;
		boolean valid = false;
		for (int i = 0; i< len && !valid; i++) {
			if (!visited[i] && graph[i][current] > 0) {
				System.out.println(i);
				valid = isValid(graph, count, visited, origin, i);
			} else if (visited[i] && graph[i][current] > 0 
					&& i == origin && count == graph.length) {
				return true;
			}
		}
		visited[current] = false;
		return valid;
	}
	
	public static void main(String[] args) {
		
		Vertex v1 = new Vertex(0, 0, 0);
		Vertex v2 = new Vertex(0, 0, 0);
		Vertex v3 = new Vertex(0, 0, 0);
		Vertex v4 = new Vertex(0, 0, 0);
		Vertex v5 = new Vertex(0, 0, 0);
		Edge e1 = new Edge(v1, v2);
		Edge e2 = new Edge(v1, v3);
		Edge e3 = new Edge(v2, v4);
		Edge e4 = new Edge(v3, v4);
		Edge e5 = new Edge(v4, v5);
		Polygon p = new Polygon();
		p.addEdge(e1);
		p.addEdge(e2);
		p.addEdge(e3);
		p.addEdge(e4);
		//p.addEdge(e5);
		System.out.println(p.isValid());
	}*/

}
