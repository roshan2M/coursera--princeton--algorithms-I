package week_1.quizzes;

import com.google.common.graph.ImmutableGraph;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.Stack;

public class UndirectedGraphsQuiz {

	/**
	 * Runtime: O(V+E), V is the number of vertices, E is the number of edges
	 *
	 * @param G
	 * @param s: source node in graph G
	 * @return boolean array of visited nodes in G
	 */
	public static boolean[] iterativeDFS(ImmutableGraph G, int s) {
		boolean[] visited = new boolean[G.nodes().size()];
		Stack<Integer> nodeStack = new Stack<>();
		nodeStack.push(s);

		while (!nodeStack.isEmpty()) {
			s = nodeStack.pop();

			if (!visited[s]) {
				visited[s] = true;
				for (Object i : G.adjacentNodes(s)) {
					nodeStack.push((int) i);
				}
			}
		}
		return visited;
	}

	/**
	 * Runtime: O(V+E), V is the number of vertices, E is the number of edges
	 *
	 * @param G: connected graph with no cycles
	 * @return length of longest single path in G
	 */
	public static int graphDiameter(ImmutableGraph G) {
		Pair<Integer, Integer> path = longestPath(G, 0);
		Pair<Integer, Integer> longestPath = longestPath(G, path.getKey());

		return longestPath.getValue();
	}

	/**
	 * Runtime: O(V+E), V is the number of vertices, E is the number of edges
	 *
	 * @param G: connected graph with no cycles
	 * @param u: node to find the farthest node from
	 * @return pair containing farthest node from u (key) and distance to u (value)
	 */
	private static Pair<Integer, Integer> longestPath(ImmutableGraph G, int u) {
		int[] distance = new int[G.nodes().size()];
		boolean[] visited = new boolean[G.nodes().size()];
		Stack<Integer> nodeStack = new Stack<>();
		nodeStack.push(u);

		while (!nodeStack.isEmpty()) {
			int m = nodeStack.pop();

			for (Object neighbour : G.adjacentNodes(m)) {
				int n = (int) neighbour;
				if (!visited[n]) {
					nodeStack.push(n);
					visited[n] = true;
					distance[n] = distance[m] + 1;
				}
			}
		}

		int index = u, maxLength = 0;
		for (int i = 0; i < distance.length; i++) {
			if (distance[i] > maxLength) {
				index = i;
				maxLength = distance[i];
			}
		}
		return new Pair<>(index, maxLength);
	}

	/**
	 * Runtime: O(V*(V+E)), V is the number of vertices, E is the number of edges
	 *
	 * @param G: connected graph G with no cycles
	 * @return a node in the graph such that the maximum distance from
	 *         any other node is minimized (naive method)
	 */
	public static int graphCenterNaive(ImmutableGraph G) {
		int node = 0, minMaxPathLength = longestPath(G, node).getValue();
		int maxPathLength;

		for (Object i : G.nodes()) {
			maxPathLength = longestPath(G, (int) i).getValue();
			if (maxPathLength < minMaxPathLength) {
				minMaxPathLength = maxPathLength;
				node = (int) i;
			}
		}
		return node;
	}

	/**
	 * Runtime: O(V+E), V is the number of vertices, E is the number of edges
	 *
	 * @param G: connected graph G with no cycles
	 * @return a node in the graph such that the maximum distance from
	 *         any other node is minimized
	 */
	public static int graphCenter(ImmutableGraph G) {
		return 0;
	}

}
