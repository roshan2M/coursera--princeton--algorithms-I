package week_1.lectures.undirected_graphs;

import com.google.common.graph.Graph;

public class DepthFirstPaths {

	private boolean[] visited;
	private int[] edgeTo;

	public DepthFirstPaths(Graph G, int s) {
		visited = new boolean[G.edges().size()];
		edgeTo = new int[G.edges().size()];
		dfs(G, s);
	}

	private void dfs(Graph G, int v) {
		visited[v] = true;
		for (Object w : G.adjacentNodes(v)) {
			if (!visited[(int) w]) {
				dfs(G, (int) w);
				edgeTo[(int) w] = v;
			}
		}
	}

}
