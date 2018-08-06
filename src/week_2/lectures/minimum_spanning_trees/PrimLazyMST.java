package week_2.lectures.minimum_spanning_trees;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

public class PrimLazyMST {

	private boolean[] marked;
	private Queue<Edge> mst;
	private MinPQ<Edge> pq;

	public PrimLazyMST(EdgeWeightedGraph graph) {
		marked = new boolean[graph.V()];
		mst = new Queue<>();
		pq = new MinPQ<>();
		visit(graph, 0);

		while (!pq.isEmpty()) {
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
			if (marked[v] && marked[w]) continue;
			mst.enqueue(e);
			if (!marked[v]) visit(graph, v);
			else if (!marked[w]) visit(graph, w);
		}
	}

	private void visit(EdgeWeightedGraph graph, int v) {
		marked[v] = true;
		for (Edge e : graph.adj(v)) {
			if (!marked[e.other(v)]) {
				pq.insert(e);
			}
		}
	}

	public Iterable<Edge> edges() {
		return mst;
	}

}
