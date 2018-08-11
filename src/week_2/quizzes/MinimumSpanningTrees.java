package week_2.quizzes;

import edu.princeton.cs.algs4.*;

public class MinimumSpanningTrees {

	/**
	 * For finding the Minimum Bottleneck Spanning Tree (MBST),
	 * just find the MST of the graph in O(E*log E) time. The MST
	 * must be the MBST.
	 *
	 * Pf: Assume G is a weighted undirected graph with MST T and
	 * T' is another spanning tree. Assume there exists edge e in T
	 * such that e.weight() > f.weight() for all edges f in T'.
	 *
	 * Then adding e joins v, w in T' and yields a cycle C. However,
	 * by the cut property, there is a shorter crossing between v, w,
	 * contradicting that T is an MST.
	 */

	/**
	 *
	 * @param G is an edge-weighted graph
	 * @param e is an edge in G
	 * @return true if the edge is in the MST, false otherwise
	 */
	public static boolean edgeInMST(EdgeWeightedGraph G, Edge e) {
		boolean[] marked = new boolean[G.V()];
		double givenEdgeWeight = e.weight();
		for (Edge edge : G.edges()) {
			if (edge.weight() < givenEdgeWeight) {
				int v = edge.either();
				marked[v] = true;
				marked[edge.other(v)] = true;
			}
		}
		int v = e.either();
		return !(marked[v] && marked[e.other(v)]);
	}

	/**
	 *
	 * @param G is an edge-weighted graph
	 * @return iterable object of edges representing the minimum feedback edge set of G
	 */
	public static Iterable<Edge> MFES(EdgeWeightedGraph G) {
		MaxPQ<Edge> pq = new MaxPQ<>();
		Queue<Edge> mfes = new Queue<>();
		for (Edge e : G.edges()) {
			pq.insert(e);
		}
		UF graphUF = new UF(G.V());

		while (!pq.isEmpty()) {
			Edge e = pq.delMax();
			int v = e.either();
			int w = e.other(v);
			if (!graphUF.connected(v, w)) {
				graphUF.union(v, w);
			} else {
				mfes.enqueue(e);
			}
		}
		return mfes;
	}

}
