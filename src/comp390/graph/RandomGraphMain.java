package comp390.graph;

import java.util.Set;

public class RandomGraphMain {

	public static void main(String[] args) {
		
		DirectedGraph g = new DirectedGraphImpl();
		
		int vertex_count = 10000;		
		Vertex[] vertices = new Vertex[vertex_count];
		for (int i=0; i<vertex_count; i++) {
			vertices[i] = new LabeledVertex(""+i);
			g.addVertex(vertices[i]);
		}
		
		int edge_count = 20000;
		while (edge_count > 0) {
			Vertex src = vertices[(int) (vertices.length*Math.random())];
			Vertex dest = vertices[(int) (vertices.length*Math.random())];
			if (!g.hasEdge(src,  dest)) {
				g.addEdge(src, dest);
				edge_count--;
			}
		}
		
		DirectedPath p = g.findPath(vertices[0], vertices[1]);
		if (p == null) {
			System.out.println("No path");
		} else {
			System.out.println(p);
		}
	}
}
