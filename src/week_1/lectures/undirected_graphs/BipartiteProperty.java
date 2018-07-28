package week_1.lectures.undirected_graphs;

import com.google.common.graph.ImmutableGraph;

public class BipartiteProperty {

	static final int RED = 1;
	static final int BLUE = 2;

	int[] colourVertices;
	boolean[] visited;
	boolean isBipartite;

	/**
	 * @param G: connected graph
	 */
	public BipartiteProperty(ImmutableGraph G) {
		colourVertices = new int[G.nodes().size()];
		visited = new boolean[G.nodes().size()];

		colourVertices[0] = RED;
		isBipartite = isBipartite(G, 0);
	}

	private boolean isBipartite(ImmutableGraph G, int s) {
		for (Object i : G.adjacentNodes(s)) {
			int v = (int) i;
			if (!visited[v]) {
				visited[v] = true;
				colourVertices[v] = (colourVertices[s] == RED ? BLUE : RED);

				if (!isBipartite(G, v)) {
					return false;
				}
			} else if (colourVertices[v] == colourVertices[s]) {
				return false;
			}
		}
		return true;
	}

}
