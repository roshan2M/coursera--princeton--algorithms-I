package week_1.quizzes;

import com.google.common.graph.*;
import week_1.lectures.directed_graphs.TopologicalSort;
import week_1.lectures.undirected_graphs.BreadthFirstPaths;

import java.util.Stack;

public class DirectedGraphsQuiz {

	/**
	 * Time: O(V*(V+E))
	 * Space: O(V+E)
	 *
	 * @param G: directed graph
	 * @return An array of integers representing the shortest directed
	 *         cycle in G, or null if there is no cycle
	 */
	public static Stack<Integer> shortestDirectedCycle(MutableGraph G) {
		Stack<Integer> shortestCycle = null;
		ImmutableGraph G_R = (ImmutableGraph) Graphs.transpose(G);
		int cycleLength = G.nodes().size() + 1;

		for (Object i : G.nodes()) {
			BreadthFirstPaths bfs = new BreadthFirstPaths(G_R, (int) i);
			int[] distanceTo = bfs.distance();

			for (Object j : G.adjacentNodes(i)) {
				if (bfs.hasPathTo((int) j) && (distanceTo[(int) j] + 1) < cycleLength) {
					cycleLength = distanceTo[(int) j] + 1;
					shortestCycle = new Stack<Integer>();

					for (int vertex : bfs.pathTo((int) j)) {
						shortestCycle.push(vertex);
					}
					shortestCycle.push((int) j);
				}
			}
		}
		return shortestCycle;
	}

	/**
	 * Time:
	 * Space:
	 *
	 * @param G: directed acyclic graph
	 * @return An integer array of the Hamiltonian path in G
	 *         if one exists, null otherwise
	 */
	public static int[] hamiltonianPath(ImmutableGraph G) {
		Stack<Integer> graphOrder = (new TopologicalSort(G)).reversePost();
		int[] path = new int[G.nodes().size()];

		int index = 0;
		int first = graphOrder.pop();
		int second;
		while (!graphOrder.isEmpty()) {
			second = graphOrder.pop();
			boolean isAdjacentTo = false;
			for (Object i : G.adjacentNodes(first)) {
				isAdjacentTo = isAdjacentTo || (int) i == second;
			}
			if (!isAdjacentTo) return null;
			path[index] = first;
			first = second;
			index++;
		}
		return path;
	}

}
