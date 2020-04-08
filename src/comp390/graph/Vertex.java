package comp390.graph;

public interface Vertex {

	void mark();
	void unmark();
	boolean isMarked();

	default void toggleMark() {
		if (isMarked()) {
			unmark();
		} else {
			mark();
		}
	}
}
