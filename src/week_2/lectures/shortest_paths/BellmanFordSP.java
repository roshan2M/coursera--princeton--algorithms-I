package week_2.lectures.shortest_paths;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;

public class BellmanFordSP {

	private DirectedEdge[] edgeTo;
	private double[] distTo;

	public BellmanFordSP(EdgeWeightedDigraph G, int s) throws Exception {
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];

		for (int i = 0; i < G.V(); i++) {
			distTo[i] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;

		for (int i = 0; i < G.V(); i++) {
			for (DirectedEdge e : G.edges()) {
				int v = e.from(), w = e.to();
				if (distTo[w] > distTo[v] + e.weight()) {
					distTo[w] = distTo[v] + e.weight();
					edgeTo[w] = e;
				}
			}
		}

		for (DirectedEdge e : G.edges()) {
			int v = e.from(), w = e.to();
			if (distTo[w] > distTo[v] + e.weight()) {
				throw new Exception("Graph contains a negative cycle.");
			}
		}
	}

}
