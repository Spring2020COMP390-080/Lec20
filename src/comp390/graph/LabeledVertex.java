package comp390.graph;

public class LabeledVertex implements Vertex {

	private String _label;
	
	public LabeledVertex(String label) {
		if (label == null) {
			throw new IllegalArgumentException();
		}
		
		_label = label;		
	}
	
	@Override
	public String toString() {
		return _label;
	}
	
	public String getLabel() {
		return _label;
	}
}
