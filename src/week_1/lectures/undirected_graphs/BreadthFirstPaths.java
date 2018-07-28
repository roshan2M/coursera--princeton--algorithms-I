package week_1.lectures.undirected_graphs;

import com.google.common.graph.Graph;
import com.google.common.graph.ImmutableGraph;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class BreadthFirstPaths {

	private boolean[] visited;

	public BreadthFirstPaths(ImmutableGraph G, int s) {
		visited = new boolean[G.edges().size()];
		bfs(G, s);
	}

	private void bfs(ImmutableGraph G, int s) {
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		visited[s] = true;
		queue.offer(s);

		while (!queue.isEmpty()) {
			int v = queue.poll();

			for (Object i : G.adjacentNodes(v)) {
				int w = (int) i;
				if (!visited[w]) {
					queue.offer(w);
					visited[w] = true;
				}
			}
		}
	}

	public boolean[] visited() {
		return this.visited;
	}

}
