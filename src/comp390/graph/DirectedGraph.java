package comp390.graph;

import java.util.List;
import java.util.Set;

public interface DirectedGraph extends Cloneable {

	void addVertex(Vertex v);
	void removeVertex(Vertex v);
	boolean hasVertex(Vertex v);
	int getInDegreeOfVertex(Vertex v);
	Vertex[] getAdjacent(Vertex v);

	void addEdge(Vertex from, Vertex to);
	void removeEdge(Vertex from, Vertex to);
	boolean hasEdge(Vertex from, Vertex to);
	DirectedEdge findEdge(Vertex from, Vertex to);
	
	Set<Vertex> getVertices();
	Set<DirectedEdge> getEdges();
	
	DirectedGraph clone();
}
