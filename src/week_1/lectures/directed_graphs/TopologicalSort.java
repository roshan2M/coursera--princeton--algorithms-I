package week_1.lectures.directed_graphs;

import com.google.common.graph.ImmutableGraph;
import com.google.common.graph.MutableGraph;

import java.util.Stack;

public class TopologicalSort {

	private boolean[] visited;
	private Stack<Integer> reversePost;

	public TopologicalSort(ImmutableGraph G) {
		visited = new boolean[G.nodes().size()];
		reversePost = new Stack<>();

		for (Object i : G.nodes()) {
			if (!visited[(int) i]) {
				dfs(G, (int) i);
			}
		}
	}

	private void dfs(ImmutableGraph G, int v) {
		visited[v] = true;
		for (Object i : G.adjacentNodes(v)) {
			if (!visited[(int) i]) {
				dfs(G, (int) i);
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

}
