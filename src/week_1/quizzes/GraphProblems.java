package week_1.quizzes;

import com.google.common.graph.Graph;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.Stack;

public class GraphProblems {

	/**
	 * @param G
	 * @param s: source node in graph G
	 * @return boolean array of visited nodes in G
	 */
	public static boolean[] iterativeDFS(Graph G, int s) {
		boolean[] visited = new boolean[G.nodes().size()];
		Stack<Integer> nodeStack = new Stack<>();
		nodeStack.push(s);

		while (!nodeStack.isEmpty()) {
			s = nodeStack.peek();
			nodeStack.pop();
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
	 * @param G: connected graph with no cycles
	 * @return length of longest single path in G
	 */
	public static int longestSimplePath(Graph G) {
		Pair<Integer, Integer> path = longestPath(G, 0);
		Pair<Integer, Integer> longestPath = longestPath(G, path.getKey());

		return longestPath.getValue();
	}

	/**
	 * @param G: connected graph with no cycles
	 * @param u: node to find the farthest node from
	 * @return pair containing farthest node from u (key) and distance to u (value)
	 */
	public static Pair<Integer, Integer> longestPath(Graph G, int u) {
		int[] distance = new int[G.nodes().size()];
		Arrays.fill(distance, -1);

		Stack<Integer> nodeStack = new Stack<>();
		nodeStack.push(u);

		while (!nodeStack.isEmpty()) {
			int m = nodeStack.peek();
			nodeStack.pop();

			for (Object neighbour : G.adjacentNodes(m)) {
				int n = (int) neighbour;
				if (distance[n] == -1) {
					nodeStack.push(n);
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

}
