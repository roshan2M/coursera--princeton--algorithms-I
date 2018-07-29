package week_1.lectures.undirected_graphs;

import com.google.common.graph.ImmutableGraph;

public class ConnectedComponents {

	private int[] connectedComponents;
	private boolean[] visited;
	private int index = 0;

	ConnectedComponents(ImmutableGraph G) {
		visited = new boolean[G.nodes().size()];
		connectedComponents = new int[G.nodes().size()];

		for (Object v : G.nodes()) {
			if (!visited[(int) v]) {
				addConnectedComponent(G, (int) v, index);
				index++;
			}
		}
	}

	private void addConnectedComponent(ImmutableGraph G, int v, int index) {
		connectedComponents[v] = index;
		boolean[] connectedNodes = (new DepthFirstPaths(G, v)).visited();
		for (int i = 0; i < connectedNodes.length; i++) {
			if (connectedNodes[i]) {
				connectedComponents[i] = index;
			}
		}
	}

	public int[] connectedComponents() {
		return this.connectedComponents;
	}

	public int components() {
		return this.index + 1;
	}

	public boolean connected(int v, int w) {
		return this.connectedComponents[v] == this.connectedComponents[w];
	}

}
