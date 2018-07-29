package week_1.lectures.directed_graphs;

import com.google.common.graph.ImmutableGraph;
import week_1.lectures.undirected_graphs.DepthFirstPaths;

import java.util.Stack;

public class StronglyConnectedComponents {

	private boolean[] visited;
	private int[] stronglyConnectedComponents;
	private int index = 0;

	public StronglyConnectedComponents(ImmutableGraph G) {
		visited = new boolean[G.nodes().size()];
		stronglyConnectedComponents = new int[G.nodes().size()];
		Stack<Integer> visitOrder = (new TopologicalSort(G)).reversePost();

		int currentNode;
		while (!visitOrder.isEmpty()) {
			currentNode = visitOrder.pop();
			if (!visited[currentNode]) {
				addStronglyConnectedComponent(G, currentNode);
				index++;
			}
		}
	}

	public void addStronglyConnectedComponent(ImmutableGraph G, int v) {
		boolean[] component = (new DepthFirstPaths(G, v)).visited();
		for (int i = 0; i < component.length; i++) {
			if (component[i]) stronglyConnectedComponents[i] = index;
		}
	}

	public int[] stronglyConnectedComponents() {
		return this.stronglyConnectedComponents;
	}

	public int components() {
		return this.index + 1;
	}

	public boolean stronglyConnected(int v, int w) {
		return this.stronglyConnectedComponents[v] == this.stronglyConnectedComponents[w];
	}

}
