package comp390.graph;

public class DirectedEdgeImpl implements DirectedEdge {

	private Vertex _source;
	private Vertex _destination;
	
	public DirectedEdgeImpl(Vertex source, Vertex destination) {
		_source = source;
		_destination = destination;
	}

	@Override
	public Vertex getSource() {
		return _source;
	}

	@Override
	public Vertex getDestination() {
		return _destination;
	}
	
	
}
