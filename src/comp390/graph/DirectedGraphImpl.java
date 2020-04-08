package comp390.graph;

import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class DirectedGraphImpl implements DirectedGraph {

	private Map<Vertex, List<DirectedEdge>> _adj_lists;
	private Map<Vertex, Integer> _in_degrees;
	
	public DirectedGraphImpl() {
		_adj_lists = new HashMap<Vertex, List<DirectedEdge>>();
		_in_degrees = new HashMap<Vertex, Integer>();
	}
	
	@Override
	public void addVertex(Vertex v) {
		if (hasVertex(v)) {
			// Already in the graph.
			return;
		}
		
		_adj_lists.put(v, new ArrayList<DirectedEdge>());
		_in_degrees.put(v, 0);
	}

	@Override
	public void removeVertex(Vertex v) {

		// If there are incoming edges remove them first.
		
		if (getInDegreeOfVertex(v) > 0) {
			// Only here if there is at least one 
			// inbound edge
			for (Vertex other : _adj_lists.keySet()) {
				removeEdge(other, v);
			}			
		}
		
		List<DirectedEdge> outbound_edges = _adj_lists.get(v);

		// Adjust in degree of destination vertexes of the outbound edges
		for (DirectedEdge e : outbound_edges) {
			_in_degrees.put(e.getDestination(), _in_degrees.get(e.getDestination())-1);
		}
		
		_adj_lists.remove(v);
		_in_degrees.remove(v);		
	}

	@Override
	public boolean hasVertex(Vertex v) {
		return _adj_lists.containsKey(v);
	}
		
	@Override
	public int getInDegreeOfVertex(Vertex v) {
		return _in_degrees.get(v);
	}

	@Override
	public Vertex[] getAdjacent(Vertex v) {
		List<DirectedEdge> edge_list = _adj_lists.get(v);
		
		Vertex[] adjacent = new Vertex[edge_list.size()];
		for (int i=0; i<edge_list.size(); i++) {
			adjacent[i] = edge_list.get(i).getDestination();
		}
		return adjacent;
	}

	@Override
	public void addEdge(Vertex from, Vertex to) {
		
		if (!hasVertex(from) || !hasVertex(to)) {
			throw new IllegalArgumentException("Either from or to is not in graph");
		}
		
		if (hasEdge(from, to)) {
			return;
		}
		
		_adj_lists.get(from).add(new DirectedEdgeImpl(from, to));
		_in_degrees.put(to, _in_degrees.get(to)+1);
	}

	@Override
	public void removeEdge(Vertex from, Vertex to) {
		DirectedEdge edge = findEdge(from, to);
		
		if (edge != null) {
			_adj_lists.get(from).remove(edge);
			_in_degrees.put(to, _in_degrees.get(to)-1);
		}
	}

	@Override
	public boolean hasEdge(Vertex from, Vertex to) {
		return findEdge(from, to) != null;
	}

	@Override
	public DirectedEdge findEdge(Vertex from, Vertex to) {
		if (!hasVertex(from) || !hasVertex(to)) {
			return null;
		}

		List<DirectedEdge> edge_list = _adj_lists.get(from);
		
		for(DirectedEdge e : edge_list) {
			if (e.getDestination() == to) {
				return e;
			}
		}
		return null;
	}
	
	
	@Override
	public Set<Vertex> getVertices() {
		return _adj_lists.keySet();
	}

	@Override
	public Set<DirectedEdge> getEdges() {
		
		Set<DirectedEdge> result = new HashSet<DirectedEdge>();
		for (List<DirectedEdge> edge_list : _adj_lists.values()) {
			for (DirectedEdge e : edge_list) {
				result.add(e);
			}
		}
		return result;
	}
	
	@Override
	public DirectedGraph clone() {
		DirectedGraph clone = new DirectedGraphImpl();
		for (Vertex v : getVertices()) {
			clone.addVertex(v);
		}
		for (DirectedEdge e : getEdges()) {
			clone.addEdge(e.getSource(), e.getDestination());
		}
		return clone;
	}
	


}
