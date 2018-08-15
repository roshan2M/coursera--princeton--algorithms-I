package week_3.lectures.max_cut_min_flow;

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.Queue;

public class FordFulkerson {

	private boolean[] marked;
	private FlowEdge[] edgeTo;
	private double flow;

	public FordFulkerson(FlowNetwork G, int s, int t) {
		flow = 0;
		while (hasAugmentingPath(G, s, t)) {
			double bottleneck = Double.POSITIVE_INFINITY;
			for (int v = t; v != s; v = edgeTo[v].other(v)) {
				bottleneck = Math.min(bottleneck, edgeTo[v].residualCapacityTo(v));
			}
			for (int v = t; v != s; v = edgeTo[v].other(v)) {
				edgeTo[v].addResidualFlowTo(v, bottleneck);
			}
			flow += bottleneck;
		}
	}

	public boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
		marked = new boolean[G.V()];
		edgeTo = new FlowEdge[G.V()];
		Queue<Integer> search = new Queue<>();
		search.enqueue(s);

		while (!search.isEmpty()) {
			int v = search.dequeue();
			for (FlowEdge e : G.adj(v)) {
				int w = e.other(v);
				if (e.residualCapacityTo(w) > 0 && !marked[w]) {
					marked[w] = true;
					edgeTo[w] = e;
					search.enqueue(w);
				}
			}
		}
		return marked[t];
	}

	public double flow() {
		return flow;
	}

}
