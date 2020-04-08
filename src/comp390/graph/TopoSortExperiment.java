package comp390.graph;

import java.util.List;

public class TopoSortExperiment {

	public static void main(String[] args) throws NoTopologicalSortException {
		double edge_perc = 0.2;
		int rep_count = 10;

		for (int vcount=1000; vcount <= 30000; vcount += 1000) {
			runExperiment(vcount, edge_perc, rep_count);
		}
	}

	static void runExperiment(int vcount, double eperc, int reps) throws NoTopologicalSortException {
		long slow_time_sum = 0;
		long fast_time_sum = 0;
		
		Vertex[] vertices = new Vertex[vcount];
		for (int i=0; i<vcount; i++) {
			vertices[i] = new LabeledVertex(Integer.toString(i));
		}

		for(int i=0; i<reps;i++) {
			DirectedGraph graph = makeRandomGraph(vertices, eperc);

			long start_time = System.nanoTime();
			List<Vertex> sorted = TopologicalSort.sortFast(graph);
			long end_time = System.nanoTime();				
			fast_time_sum += end_time - start_time;

		
			start_time = System.nanoTime();
			sorted = TopologicalSort.sortSlow(graph);
			end_time = System.nanoTime();				
			slow_time_sum += end_time - start_time;
		}
		
		System.out.println(vcount + ", " + fast_time_sum/reps + ", " + slow_time_sum/reps);
	}

	static DirectedGraph makeRandomGraph(Vertex[] vertices, double eperc) {
		DirectedGraphImpl graph = new DirectedGraphImpl();
		for (Vertex v : vertices) {
			graph.addVertex(v);
		}
		
		int edge_target = (int) (vertices.length * eperc);
		int edge_count = 0;
		
		while (edge_count != edge_target) {
			int from = (int) (vertices.length * Math.random());
			int to = (int) (vertices.length * Math.random());
			if (from > to) {
				int tmp = from;
				from = to;
				to = tmp;
			}
			if (from != to && !graph.hasEdge(vertices[from], vertices[to])) {
				graph.addEdge(vertices[from], vertices[to]);
				edge_count++;
			}
		}
		return graph;
	}
}
