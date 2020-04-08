package comp390.graph;

public interface DirectedPath {

	DirectedGraph getGraph();
	Vertex getStart();
	int getLength();
	Vertex getEnd();
	Vertex getPathVertex(int path_index);
	DirectedEdge getPathEdge(int hop_index);
	boolean contains(Vertex v);
	boolean contains(DirectedEdge e);
}
