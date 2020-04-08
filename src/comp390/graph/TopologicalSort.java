package comp390.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class TopologicalSort {

	public static List<Vertex> sortSlow(DirectedGraph g) {
		DirectedGraph working_copy = g.clone();
		
		List<Vertex> sorted = new ArrayList<Vertex>();
		
		Set<Vertex> vertices = working_copy.getVertices();
		while (vertices.size() > 0) {
			boolean found = false;
			for (Vertex v : vertices) {
				if (working_copy.getInDegreeOfVertex(v) == 0) {
					sorted.add(v);
					working_copy.removeVertex(v);
					found = true;
					break;
				}
			}
			if (!found) {
				break;
			}
		}
		if (vertices.size() != 0) {
			throw new RuntimeException("Graph has cycles, no topo sort possible");
		}
		return sorted;
	}
	
	public static List<Vertex> sortFast(DirectedGraph g) {
		DirectedGraph working_copy = g.clone();
		
		List<Vertex> sorted = new ArrayList<Vertex>();
		Queue<Vertex> vq = new LinkedList<Vertex>();
		
		Set<Vertex> vertices = working_copy.getVertices();		
		
		for (Vertex v : vertices) {
			if (working_copy.getInDegreeOfVertex(v) == 0) {
				vq.add(v);
			}
		}
		
		while (vq.size() > 0) {
			Vertex next = vq.remove();
			sorted.add(next);
			Vertex[] neighbors = working_copy.getAdjacent(next);
			working_copy.removeVertex(next);
			for (Vertex neighbor : neighbors) {
				if (working_copy.getInDegreeOfVertex(neighbor) == 0) {
					vq.add(neighbor);
				}
			}
		}
		if (vertices.size() != 0) {
			throw new RuntimeException("Graph has cycles, no topo sort possible");
		}
		return sorted;
	}
}
