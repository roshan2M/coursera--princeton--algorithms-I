package week_2.lectures.minimum_spanning_trees;

import edu.princeton.cs.algs4.*;

public class KruskalMST {

	// Used to represent the MST.
	private Queue<Edge> mst = new Queue<>();

	public KruskalMST(EdgeWeightedGraph graph) {
		// Priority queue sorts by edge weights.
		MinPQ<Edge> pq = new MinPQ<>();
		for (Edge e : graph.edges()) {
			pq.insert(e);
		}

		UF uf = new UF(graph.V());
		while (!pq.isEmpty() && mst.size() < graph.V() - 1) {
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
			if (!uf.connected(v, w)) {
				mst.enqueue(e);
				uf.union(v, w);
			}
		}
	}

	public Iterable<Edge> edges() {
		return mst;
	}

}
