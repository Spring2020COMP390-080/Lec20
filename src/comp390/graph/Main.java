package comp390.graph;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		Vertex a = new LabeledVertex("A");
		Vertex b = new LabeledVertex("B");
		Vertex c = new LabeledVertex("C");
		Vertex d = new LabeledVertex("D");
		Vertex e = new LabeledVertex("E");
		Vertex f = new LabeledVertex("F");
		
		DirectedGraph g = new DirectedGraphImpl();
		g.addVertex(a);
		g.addVertex(b);
		g.addVertex(c);
		g.addVertex(d);
		g.addVertex(e);
		g.addVertex(f);
		
		g.addEdge(a,  b);
		g.addEdge(a,  f);
		g.addEdge(b, f);
		g.addEdge(b, c);
		g.addEdge(c, a);
		g.addEdge(c, e);
		g.addEdge(d, c);
		g.addEdge(e, d);
		
		List<DirectedEdge> path = g.findPath(a, d);
		
		if (path != null) {
			for (DirectedEdge edge : path) {
				System.out.println(edge.getSource() + "->" + edge.getDestination());
			}
		} else {
			System.out.println("No path");
		}
	}
}
