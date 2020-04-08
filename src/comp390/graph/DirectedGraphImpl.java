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
	
	public DirectedGraphImpl() {
		_adj_lists = new HashMap<Vertex, List<DirectedEdge>>();
	}

	@Override
	public void addVertex(Vertex v) {
		if (hasVertex(v)) {
			// Already in the graph.
			return;
		}
		
		_adj_lists.put(v, new ArrayList<DirectedEdge>());
	}

	@Override
	public void removeVertex(Vertex v) {
		
		_adj_lists.remove(v);
		
		for (Vertex other : _adj_lists.keySet()) {
			removeEdge(other, v);
		}
	}

	@Override
	public boolean hasVertex(Vertex v) {
		return _adj_lists.containsKey(v);
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
	}

	@Override
	public void removeEdge(Vertex from, Vertex to) {
		DirectedEdge edge = findEdge(from, to);
		
		if (edge != null) {
			_adj_lists.get(from).remove(edge);
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
	public DirectedPath findPath(Vertex beginning, Vertex end) {
		
		for (Vertex v : getVertices()) {
			v.unmark();
		}
		beginning.mark();
		
		Queue<DirectedPath> path_queue = new LinkedList<DirectedPath>();
		path_queue.add(new DirectedPathImpl(this, beginning));

		int num_paths_considered = 0;
		while (path_queue.size() > 0) {
			DirectedPath path = path_queue.remove();
			num_paths_considered++;
			System.out.println(num_paths_considered + " (path size: " + path.getLength() + ", queue size: " + path_queue.size() + ")");
			Vertex path_end = path.getEnd();
			if (path_end == end) {
				// Found the path.
				return path;
			}
			for (DirectedEdge e : _adj_lists.get(path_end)) {
				Vertex next_vertex = e.getDestination();
				if (!next_vertex.isMarked()) {
					next_vertex.mark();
					path_queue.add(new DirectedPathImpl(path, next_vertex));
				}
			}
		}
		return null;
	}	
}
