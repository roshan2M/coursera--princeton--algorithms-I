package week_1.lectures.undirected_graphs;

import com.google.common.graph.Graph;

public class DepthFirstPaths {

	public boolean[] visited;
	public int[] edgeTo;
	public int s;

	public DepthFirstPaths(Graph G, int s) {
		visited = new boolean[G.edges().size()];
		edgeTo = new int[G.edges().size()];
		dfs(G, s);
	}

	public void dfs(Graph G, int v) {
		visited[v] = true;
		for (Object w : G.adjacentNodes(v)) {
			if (!visited[(int) w]) {
				dfs(G, (int) w);
				edgeTo[(int) w] = v;
			}
		}
	}

}
