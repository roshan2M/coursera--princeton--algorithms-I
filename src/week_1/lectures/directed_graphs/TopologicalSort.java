package week_1.lectures.directed_graphs;

import edu.princeton.cs.algs4.EdgeWeightedDigraph;

import java.util.Stack;

public class TopologicalSort {

	private boolean[] visited;
	private Stack<Integer> reversePost;
	private int[] edgeTo;

	public TopologicalSort(EdgeWeightedDigraph G) {
		edgeTo = new int[G.V()];
		visited = new boolean[G.V()];
		reversePost = new Stack<>();

		for (int i = 0; i < G.V(); i++) {
			if (!visited[i]) {
				dfs(G, i);
			}
		}
	}

	private void dfs(EdgeWeightedDigraph G, int v) {
		visited[v] = true;
		for (int i = 0; i < G.V(); i++) {
			if (!visited[i]) {
				edgeTo[i] = v;
				dfs(G, i);
			}
		}
		reversePost.push(v);
	}

	public boolean[] visited() {
		return visited;
	}

	public Stack<Integer> reversePost() {
		return reversePost;
	}

	public int[] edgeTo() {
		return this.edgeTo;
	}

}
