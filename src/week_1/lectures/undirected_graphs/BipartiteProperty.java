package week_1.lectures.undirected_graphs;

import com.google.common.graph.Graph;

public class BipartiteProperty {

	static final int RED = 1;
	static final int BLUE = 2;

	int[] colourVertices;
	boolean[] visited;
	boolean isBipartite;

	public BipartiteProperty(Graph G) {
		colourVertices = new int[G.nodes().size()];
		visited = new boolean[G.nodes().size()];

		colourVertices[0] = RED;
		isBipartite = isBipartite(G, 0);
	}

	private boolean isBipartite(Graph G, int s) {
		for (Object i : G.nodes()) {
			if (!visited[(int) i]) {
				visited[(int) i] = true;
				colourVertices[(int) i] = colourVertices[s] == RED ? BLUE : RED;

				if (!isBipartite(G, (int) i)) {
					return false;
				}
			} else if (colourVertices[(int) i] == colourVertices[s]) {
				return false;
			}
		}
		return true;
	}

}
