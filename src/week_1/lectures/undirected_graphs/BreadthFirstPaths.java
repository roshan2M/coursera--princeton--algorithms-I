package week_1.lectures.undirected_graphs;

import com.google.common.graph.ImmutableGraph;

import java.util.PriorityQueue;
import java.util.Stack;

public class BreadthFirstPaths {

	private boolean[] visited;
	private int[] distance;
	private int[] edgeTo;

	public BreadthFirstPaths(ImmutableGraph G, int s) {
		visited = new boolean[G.nodes().size()];
		distance = new int[G.nodes().size()];
		edgeTo = new int[G.nodes().size()];
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
					distance[w] = distance[v] + 1;
					edgeTo[w] = v;
				}
			}
		}
	}

	public boolean[] visited() {
		return this.visited;
	}

	public int[] distance() {
		return this.distance;
	}

	public boolean hasPathTo(int w) {
		return this.visited[w];
	}

	public Iterable<Integer> pathTo(int w) {
		if (!hasPathTo(w)) {
			return null;
		}
		Stack<Integer> path = new Stack<Integer>();
		int x;
		for (x = w; distance[x] != 0; x = edgeTo[x]) {
			path.push(x);
		}
		path.push(x);
		return path;
	}

}
