package week_1.lectures.undirected_graphs;

import com.google.common.graph.Graph;

import java.util.LinkedList;

public class BreadthFirstPaths {

	private boolean[] visited;
	private int[] edgeTo;

	public BreadthFirstPaths(Graph G, int s) {
		visited = new boolean[G.edges().size()];
		edgeTo = new int[G.edges().size()];
		bfs(G, s);
	}

	private void bfs(Graph G, int s) {
		LinkedList<Integer> queue = new LinkedList<>();
		visited[s] = true;
		queue.add(s);

		while (queue.size() > 0) {
			int v = queue.poll();
			for (Object w : G.adjacentNodes(v)) {
				if (!visited[(int) w]) {
					queue.add((int) w);
					visited[(int) w] = true;
					edgeTo[(int) w] = v;
				}
			}
		}
	}

	public boolean[] visited() {
		return this.visited;
	}

	public int[] edgeTo() {
		return this.edgeTo;
	}

}
