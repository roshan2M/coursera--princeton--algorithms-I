package week_1.problem_set.wordnet;

/*********
 *
 * @author Roshan Munjal
 *
 * SAP Module API.
 *
 *********/

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class SAP {

	private static final int INFINITY = Integer.MAX_VALUE;

	private Digraph digraph;

	// constructor takes a digraph (not necessarily a DAG)
	public SAP(Digraph givenDigraph) {
		if (givenDigraph == null) {
			throw new IllegalArgumentException("Graph cannot be null.");
		}
		digraph = givenDigraph;
	}

	private int[] shortestAncestralPath(Iterable<Integer> v, Iterable<Integer> w) {
		int[] result = new int[2];

		// BFS takes O(V+E) time and O(V) space.
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);

		// Find shortest ancestral path using BFS.
		int shortestPathLength = INFINITY;
		int closestAncestor = -1;
		for (int i = 0; i < digraph.V(); i++) {
			if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
				int length = bfsV.distTo(i) + bfsW.distTo(i);

				if (bfsV.distTo(i) + bfsW.distTo(i) < shortestPathLength) {
					shortestPathLength = length;
					closestAncestor = i;
				}
			}
		}
		result[0] = closestAncestor == -1 ? -1 : shortestPathLength;
		result[1] = closestAncestor;
		return result;
	}

	// length of shortest ancestral path between v and w; -1 if no such path
	public int length(int v, int w) {
		int[] result = shortestAncestralPath(new ArrayList<>(v), new ArrayList<>(w));
		return result[0];
	}

	// a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
	public int ancestor(int v, int w) {
		int[] result = shortestAncestralPath(new ArrayList<>(v), new ArrayList<>(w));
		return result[1];
	}

	// length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		int[] result = shortestAncestralPath(v, w);
		return result[0];
	}

	// a common ancestor that participates in shortest ancestral path; -1 if no such path
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		int[] result = shortestAncestralPath(v, w);
		return result[1];
	}

	// testing client for SAP
	public static void main(String[] args) {
		In in = new In(args[0]);
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		while (!StdIn.isEmpty()) {
			int v = StdIn.readInt();
			int w = StdIn.readInt();
			int length   = sap.length(v, w);
			int ancestor = sap.ancestor(v, w);
			StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
		}
	}

}
