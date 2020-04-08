package comp390.graph;

public class DirectedPathImpl implements DirectedPath {

	private DirectedGraph _graph;
	private Vertex _start;
	private DirectedEdge[] _path_edges;
	
	public DirectedPathImpl(DirectedGraph graph, Vertex start) {
		if (!graph.hasVertex(start)) {
			throw new IllegalArgumentException("start not in graph");
		}
		
		_graph = graph;
		_start = start;
		_path_edges = new DirectedEdge[0];
	}
	
	public DirectedPathImpl(DirectedPath prior_path, Vertex next_vertex) {
		
		if (!prior_path.getGraph().hasVertex(next_vertex)) {
			throw new IllegalArgumentException("next_vertex not in path's graph");
		}
		
		DirectedEdge next_edge = prior_path.getGraph().findEdge(prior_path.getEnd(), next_vertex);
		
		if (next_edge == null) {
			throw new IllegalArgumentException("no edge from end of prior path to next_vertex");
		}
		
		_graph = prior_path.getGraph();
		_start = prior_path.getStart();
		_path_edges = new DirectedEdge[prior_path.getLength()+1];
		for (int hop=0; hop<prior_path.getLength(); hop++) {
			_path_edges[hop] = prior_path.getPathEdge(hop);
		}
		_path_edges[_path_edges.length-1] = next_edge;
	}
		
	@Override
	public Vertex getStart() {
		return _start;
	}

	@Override
	public int getLength() {
		return _path_edges.length;
	}

	@Override
	public Vertex getEnd() {
		if (getLength() == 0) {
			return _start;
		} else {
			return _path_edges[getLength()-1].getDestination();
		}
	}

	@Override
	public Vertex getPathVertex(int path_index) {
		if (path_index < 0 || path_index > getLength()) {
			throw new IllegalArgumentException("Path vertex index out of bounds");
		}
		if (path_index == 0) {
			return getStart();
		} else {
			return _path_edges[path_index-1].getDestination();
		}
	}

	@Override
	public DirectedEdge getPathEdge(int hop_index) {
		if ((hop_index < 0) || (hop_index >= getLength())) {
			throw new IllegalArgumentException("Hop index out of bounds");
		}
		return _path_edges[hop_index];
	}

	@Override
	public DirectedGraph getGraph() {
		return _graph;
	}

	@Override
	public boolean contains(Vertex v) {
		if (_start == v) {
			return true;
		}
		for (DirectedEdge e : _path_edges) {
			if (e.getDestination() == v) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean contains(DirectedEdge e) {
		for (DirectedEdge path_edge : _path_edges) {
			if (path_edge == e) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		String result = getStart().toString();
		for (DirectedEdge e : _path_edges) {
			result += "->" + e.getDestination();
		}
		return result;
	}

}
