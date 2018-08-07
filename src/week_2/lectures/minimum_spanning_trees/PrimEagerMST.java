package week_2.lectures.minimum_spanning_trees;

import edu.princeton.cs.algs4.*;

public class PrimEagerMST {

	private static final int INFINITY = Integer.MAX_VALUE;

	private boolean[] marked;
	private double[] distTo;
	private Edge[] edgeTo;
	private IndexMinPQ<Double> pq;

	public PrimEagerMST(EdgeWeightedGraph graph) {
		edgeTo = new Edge[graph.V()];
		distTo = new double[graph.V()];
		marked = new boolean[graph.V()];
		for (int v = 0; v < graph.V(); v++) {
			distTo[v] = INFINITY;
		}
		distTo[0] = 0.0;
		pq.insert(0, 0.0);

		visit(graph, 0);
		while (!pq.isEmpty()) {
			visit(graph, pq.delMin());
		}
	}

	public void visit(EdgeWeightedGraph graph, int v) {
		marked[v] = true;
		for (Edge e : graph.adj(v)) {
			int w = e.other(v);
			if (marked[v]) continue;
			if (e.weight() < distTo[w]) {
				edgeTo[w] = e;
				distTo[w] = e.weight();
				if (pq.contains(w)) {
					pq.changeKey(w, distTo[w]);
				} else {
					pq.insert(w, distTo[w]);
				}
			}
		}
	}

	public Edge[] edgeTo() {
		return edgeTo;
	}

	public double[] distTo() {
		return distTo;
	}

}
