package week_1.lectures.undirected_graphs;

import com.google.common.graph.ImmutableGraph;

import java.util.ArrayList;
import java.util.List;

public class ConnectedComponents {

	private List<List<Integer>> connectedComponents;
	private boolean[] visited;

	ConnectedComponents(ImmutableGraph G) {
		visited = new boolean[G.edges().size()];
		connectedComponents = new ArrayList<>();
		for (Object v : G.edges()) {
			if (!visited[(int) v]) {
				addConnectedComponent(G, (int) v);
			}
		}
	}

	private void addConnectedComponent(ImmutableGraph G, int v) {
		List<Integer> component = new ArrayList<>();
		DepthFirstPaths searchNode = new DepthFirstPaths(G, v);
		boolean[] visitedNodes = searchNode.visited();
		for (int i = 0; i < visitedNodes.length; i++) {
			if (visitedNodes[i]) {
				component.add(i);
			}
		}
		connectedComponents.add(component);
	}

	public boolean connected(int v, int w) {
		for (List<Integer> component : connectedComponents){
			if (component.contains(v) && component.contains(w)) {
				return true;
			}
		}
		return false;
	}

	public int count() {
		return this.connectedComponents.size();
	}

	public List<List<Integer>> connectedComponents() {
		return this.connectedComponents;
	}

	public boolean[] visited() {
		return this.visited;
	}

}
