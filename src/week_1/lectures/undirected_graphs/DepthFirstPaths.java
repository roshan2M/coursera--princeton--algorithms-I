package week_1.lectures.undirected_graphs;

import com.google.common.graph.ImmutableGraph;

public class DepthFirstPaths {

	private boolean[] visited;

	public DepthFirstPaths(ImmutableGraph G, int v) {
		visited = new boolean[G.nodes().size()];
		dfs(G, v);
	}

	private void dfs(ImmutableGraph G, int v) {
		visited[v] = true;
		for (Object w : G.adjacentNodes(v)) {
			if (!visited[(int) w]) {
				dfs(G, (int) w);
			}
		}
	}

	public boolean[] visited() {
		return this.visited;
	}

}
