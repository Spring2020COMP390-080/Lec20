package comp390.graph;

public class LabeledVertex implements Vertex {

	private String _label;
	private boolean _mark;
	
	public LabeledVertex(String label) {
		if (label == null) {
			throw new IllegalArgumentException();
		}
		
		_label = label;		
		_mark = false;
	}
	
	@Override
	public String toString() {
		return _label;
	}
	
	public String getLabel() {
		return _label;
	}

	@Override
	public void mark() {
		_mark = true;
	}

	@Override
	public void unmark() {
		_mark = false;
	}

	@Override
	public boolean isMarked() {
		return _mark;
	}
}
